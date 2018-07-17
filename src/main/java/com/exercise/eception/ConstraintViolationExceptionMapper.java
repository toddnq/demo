package com.exercise.eception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.internal.util.privilegedactions.GetConstraintValidatorList;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	@Override
	public Response toResponse(ConstraintViolationException arg0) {
		String responseBody = getMessage(arg0);
		return Response.status(Response.Status.BAD_REQUEST).entity(responseBody).build();
	}

	private String getMessage(ConstraintViolationException arg0) {
		ConstraintViolation<?> constraintViolation = getConstraintValidation(arg0);
		String beanErrorCode = getBeanErrorMessage(constraintViolation);
		return beanErrorCode;
	}

	private String getBeanErrorMessage(ConstraintViolation<?> constraintViolation) {
		String violatedType = getViolatedType(constraintViolation);
		switch(violatedType) {
			case "Size": 	return "PROJECT001: TRAINING_ERROR_EMAIL Size is minimum 7 characters";
			case "Email": 	return "PROJECT001: TRAINING_ERROR_EMAIL wrong format";
			default: 		return "DEFAULT";
		}
	}

	private String getViolatedType(ConstraintViolation<?> constraintViolation) {
		ConstraintDescriptor<?> constraintDescriptor = constraintViolation.getConstraintDescriptor();
		return constraintDescriptor.getAnnotation().annotationType().getSimpleName();
	}

	private ConstraintViolation<?> getConstraintValidation(ConstraintViolationException arg0) {
		return arg0.getConstraintViolations().iterator().next();
	}
}
