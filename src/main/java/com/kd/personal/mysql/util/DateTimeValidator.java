package com.kd.personal.mysql.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements ConstraintValidator<DateTimeValid, String> {
    private String dateFormat;
    private boolean exactLength;
    private boolean nullAllowed;


    @Override
    public void initialize(DateTimeValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        exactLength = constraintAnnotation.exactLength();
        nullAllowed = constraintAnnotation.nullAllowed();
        dateFormat = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()) {
            return nullAllowed;
        }
        if(exactLength && dateFormat.length() != s.length()){
            return false;
        }

        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try{
            sdf.parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
