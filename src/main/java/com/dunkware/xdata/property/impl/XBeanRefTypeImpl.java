package com.dunkware.xdata.property.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.validation.XPropertyValidator;

public class XBeanRefTypeImpl implements XBeanRefType {

	private String label;
	private String name; 
	private XBeanType type; 
	
	private List<XPropertyValidator> validators = new ArrayList<XPropertyValidator>();
	
	public XBeanRefTypeImpl(String name, String label, XBeanType type) {
		this.name = name; 
		this.type = type;
		this.label = label;
	}
	
	@Override
	public String getName() {
		return name; 
	}

	@Override
	public XBeanType getType() {
		return type;
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
	
	
	
	
	

}
