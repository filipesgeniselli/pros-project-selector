package br.com.filisg.prosprojectselector.utils.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class AcceptedValuesValidatorImpl implements ConstraintValidator<AcceptedValuesValidator, String> {

    List<String> valueList = null;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return valueList.contains(value.toLowerCase());
    }

    @Override
    public void initialize(AcceptedValuesValidator constraintAnnotation) {
        valueList = Arrays.asList(constraintAnnotation.acceptedValues());
    }
    
}
