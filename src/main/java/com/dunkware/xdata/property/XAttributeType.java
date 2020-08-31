package com.dunkware.xdata.property;

import com.dunkware.xdata.validation.XPropertyValidator;

public interface XAttributeType extends XPropertyType {
	
	String getFormat();
	
	int getDataType();
	
	String getName();
	
	
}