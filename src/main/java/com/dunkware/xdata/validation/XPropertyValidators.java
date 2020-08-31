package com.dunkware.xdata.validation;

import com.dunkware.xdata.exceptions.XDataException;
import com.dunkware.xdata.validation.validators.XNotNullValidator;

public class XPropertyValidators {

	public static final int NotNullValidator = 1;

	public static XPropertyValidator createValidator(int validatorId) throws XDataException {
		switch (validatorId) {
		case NotNullValidator:
			return new XNotNullValidator();
			
		}
		throw new XDataException("Attribute Validator ID " + validatorId + " invalid");

	}
}
