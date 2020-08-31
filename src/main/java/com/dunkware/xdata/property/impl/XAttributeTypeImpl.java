package com.dunkware.xdata.property.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.validation.XPropertyValidator;

public class XAttributeTypeImpl implements XAttributeType {
	
	private String label;
	private String name; 
	private String format;
	private int dataType; 
	
	private List<XPropertyValidator> validators = new ArrayList<XPropertyValidator>();
	
	public XAttributeTypeImpl(String name, String label, int dataType, String format) {
		this.name = name;
		this.label = label;
		this.format = format;
		this.dataType = dataType;
	}

	@Override
	public String getName() {
		return name; 
	}

	@Override
	public String getFormat() {
		return format; 
	}

	@Override
	public int getDataType() {
		return dataType;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public List<XPropertyValidator> getValidators() {
		return validators;
	}

	@Override
	public void addValidator(XPropertyValidator validator) {
		this.validators.add(validator);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		XAttributeTypeImpl clone = new XAttributeTypeImpl(this.name, this.label, this.dataType, this.format);
		return clone;
	}
	
	
	
	
	
	
	
	
	
	

}
