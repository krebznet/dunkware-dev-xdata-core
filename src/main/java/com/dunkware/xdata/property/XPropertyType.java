package com.dunkware.xdata.property;

import java.util.List;

import com.dunkware.xdata.validation.XPropertyValidator;

public interface XPropertyType {
	
	String getName();
	
	String getLabel();
	
	List<XPropertyValidator> getValidators();
	
	public void addValidator(XPropertyValidator validator);

}
