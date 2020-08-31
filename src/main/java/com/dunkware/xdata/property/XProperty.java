package com.dunkware.xdata.property;

import com.dunkware.xdata.validation.XValidationException;

public interface XProperty {

	boolean isNull();
	
	String getName();
	
	String getLabel();
	
	void validate() throws XValidationException;
}
