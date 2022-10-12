package com.example.nossocartaodocumentacao.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UniqueValidator.class })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "{Já existe no banco de dados}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();
}
