package com.dunkware.xdata.property.impl;

import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.list.XList;
import com.dunkware.xdata.list.impl.XListImpl;
import com.dunkware.xdata.property.XListRef;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.validation.XPropertyValidator;
import com.dunkware.xdata.validation.XValidationException;

public class XListRefImpl implements XListRef {

	private XList list = null;
	private XListRefType type; 
	
	public XListRefImpl(XListRefType type) {
		this.type = type; 
		list = new XListImpl(type.getType());
	}
	
	@Override
	public String getName() {
		return type.getName();
	}

	@Override
	public XList getValue() {
		return list;
	}
	
	@Override
	public void setValue(XList list) throws XDataRuntimeException {
		if(list.getType().getName().equals(type.getType().getName()) == false) { 
			throw new XDataRuntimeException("Illegal List Value, BeanType does not match ref type");
		}
		this.list = list;
	}

	@Override
	public String getLabel() {
		return type.getLabel();
	}

	@Override
	public XListRefType getType() {
		return type;
	}

	@Override
	public boolean isNull() {
		if(list == null)
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
