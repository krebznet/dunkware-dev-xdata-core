package com.dunkware.xdata.validation;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xdata.property.XProperty;

public abstract class XPropertyValidator {
	
	protected Map<String,Serializable> properties = new ConcurrentHashMap<String,Serializable>();
	
	public Map<String,Serializable> getProperties() { 
		return properties;
	}
	
	public abstract int getValidatorId();
	
	public void setProperties(Map<String,Serializable> properties) { 
		this.properties = properties;
	}
	
	public abstract void validate(XProperty property) throws XValidationException;

}
