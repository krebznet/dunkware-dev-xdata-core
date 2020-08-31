package com.dunkware.xdata.bean.impl;

import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.property.XBeanRef;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.validation.XPropertyValidator;
import com.dunkware.xdata.validation.XValidationException;

public class XBeanRefImpl implements XBeanRef  {

	private XBeanRefType type;
	private XBean bean = null;
	
	public XBeanRefImpl(XBeanRefType type) {
		this.type = type;
	}
	
	@Override
	public void setValue(XBean bean) throws XDataRuntimeException {
		if(bean.getType().equals(type.getType()) == false) { 
			throw new XDataRuntimeException("Invalid Bean Type");
		}
		this.bean = bean;
		
	}
	@Override
	public String getName() {
		return type.getName();
	}

	@Override
	public XBean getValue() {
		return bean;
	}
		
	@Override
	public String getLabel() {
		return type.getLabel();
	}
	@Override
	public XBeanRefType getType() {
		return type;
	}

	@Override
	public boolean isNull() {
		if(bean == null)
			return true;
		return false;
	}

	@Override
	public void validate() throws XValidationException {
		for (XPropertyValidator validator : type.getValidators()) {
			validator.validate(this);
		}
	}
	
	
	
	
	

	
	
	
}
