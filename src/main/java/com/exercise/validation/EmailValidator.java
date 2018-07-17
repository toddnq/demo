package com.exercise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

	@Override
	public void initialize(Email arg0) {
	}
	
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		String emailRegex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		return arg0.matches(emailRegex);
	}

	

}
