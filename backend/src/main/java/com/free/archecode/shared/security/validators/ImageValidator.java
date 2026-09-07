package com.free.archecode.shared.security.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.free.archecode.shared.security.validators.imp.ImageValidatorImp;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ImageValidatorImp.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageValidator {
    String message() default "The file is not an image";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}
