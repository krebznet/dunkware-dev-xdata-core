package com.dunkware.xdata.validation.validators;

import com.dunkware.xdata.XEvents;
import com.dunkware.xdata.property.XAttribute;
import com.dunkware.xdata.property.XBeanRef;
import com.dunkware.xdata.property.XListRef;
import com.dunkware.xdata.property.XProperty;
import com.dunkware.xdata.validation.XPropertyValidator;
import com.dunkware.xdata.validation.XPropertyValidators;
import com.dunkware.xdata.validation.XValidationException;

public class XNotNullValidator extends XPropertyValidator {


	public XNotNullValidator() {
		
	}

	@Override
	public int getValidatorId() {
		return XPropertyValidators.NotNullValidator;
	}

	@Override
	public void validate(XProperty prop) throws XValidationException {
		if (prop instanceof XAttribute) {
			XAttribute bute = (XAttribute) prop;
			if(bute.isNull()) { 
				throw new XValidationException("Attribute " + bute.getName() + " cannot be null");
			}	
		}
		if (prop instanceof XBeanRef) {
			XBeanRef beanRef = (XBeanRef) prop;
			if(beanRef.getValue() == null) { 
				throw new XValidationException("Bean Ref " + prop.getName() + " cannot be null");
			}
		}
		
		if (prop instanceof XListRef) {
			XListRef listRef = (XListRef) prop;
			if(listRef.getValue() == null) { 
				throw new XValidationException("Bean List " + prop.getName() + " cannot be null");
			}

		}
		
		
	}
	
	
	
	
}
