package com.example.nossocartaodocumentacao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @Autowired
    private EntityManager entityManager;


    @Override
    public void initialize(Unique params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + " =:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "foi encontrado mais de um " + klass + " com o atributo " + domainAttribute + " = " + value);

        String menssagem = String.format("com valor %s já existe em %s", value, klass.getSimpleName());

        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(menssagem).addConstraintViolation();

        return list.isEmpty();
    }
}
