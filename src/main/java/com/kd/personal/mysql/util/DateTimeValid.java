package com.kd.personal.mysql.util;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = com.kd.personal.mysql.util.DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeValid {
    String message() default "Date format invalid";
    String pattern() default "yyyy-MM-dd";
    boolean exactLength() default true;
    boolean nullAllowed() default false;
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};

}
