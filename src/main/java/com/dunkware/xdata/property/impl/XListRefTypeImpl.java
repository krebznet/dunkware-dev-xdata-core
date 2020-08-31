package com.dunkware.xdata.property.impl;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.validation.XPropertyValidator;

public class XListRefTypeImpl implements XListRefType {
	
	private String name; 
	private String label; 
	private XBeanType type;
	
	private List<XPropertyValidator> validators = new ArrayList<XPropertyValidator>();
	
	public XListRefTypeImpl(String name, String label, XBeanType beanType) { 
		this.name = name; 
		this.type = beanType;
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
