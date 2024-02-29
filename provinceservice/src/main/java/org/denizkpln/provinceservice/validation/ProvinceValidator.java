package org.denizkpln.provinceservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ProvinceValidator implements ConstraintValidator<ProvinceValue,String> {
    @Override
    public void initialize(ProvinceValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isNext=true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))){
                isNext=false;
            }
        }
        return isNext;
    }
}
