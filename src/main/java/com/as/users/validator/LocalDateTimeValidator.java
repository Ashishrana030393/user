package com.as.users.validator;

import com.as.users.enums.DateTimeType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeValidator implements ConstraintValidator<LocalDateTimeFormat,String> {
    private String pattern;
    private DateTimeType dateTimeType;
    public void initialize(LocalDateTimeFormat constraintAnnotation){
        this.pattern = constraintAnnotation.pattern();
        this.dateTimeType = constraintAnnotation.dateTimeType();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value== null || value.isEmpty()){
            return true;
        }
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            if(DateTimeType.Date.equals(dateTimeType)){
                LocalDate.parse(value,formatter);
            }
            else if(DateTimeType.Time.equals(dateTimeType)){
                LocalTime.parse(value,formatter);
            }
            else{
                LocalDateTime.parse(value,formatter);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
