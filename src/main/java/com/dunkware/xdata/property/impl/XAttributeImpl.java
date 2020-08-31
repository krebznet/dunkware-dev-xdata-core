package com.dunkware.xdata.property.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.commons.util.datatype.DataTypes;
import com.dunkware.commons.util.dtime.DDate;
import com.dunkware.commons.util.dtime.DDateTime;
import com.dunkware.commons.util.dtime.DTime;
import com.dunkware.commons.util.observable.DObservableImpl;
import com.dunkware.xdata.XEvents;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.property.XAttribute;
import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.validation.XPropertyValidator;
import com.dunkware.xdata.validation.XValidationException;

public class XAttributeImpl extends DObservableImpl implements XAttribute {

	private volatile Serializable value = null;
	private XAttributeType type; 
	private ConcurrentHashMap<String, Object> metadata = new ConcurrentHashMap<String, Object>();
	
	public XAttributeImpl(XAttributeType type) {
		this.type = type;
	}
	
	@Override
	public String getName() {
		return type.getName();
	}
	
	@Override
	public String getLabel() {
		return type.getLabel();
	}

	@Override
	public Serializable getValue() {
		return value; 
	}
	
	@Override
	public boolean isNull() {
		if(value == null)
			return true;
		return false;
	}

	@Override
	public String getString() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof String) {
			String string = (String) value;
			return string;
		}
		throw new XDataRuntimeException("Cannot get String value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public Double getDouble() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof Double) {
			Double string = (Double) value;
			return string;
		}
		throw new XDataRuntimeException("Cannot get Double value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public Integer getInteger() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof Integer) {
			Integer string = (Integer) value;
			return string;
		}
		throw new XDataRuntimeException("Cannot get Integer value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public DTime getTime() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof DTime) {
			DTime dtime = (DTime) value;
			return dtime;
		}
		throw new XDataRuntimeException("Cannot get Time value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public DDate getDate() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof DDate) {
			DDate dtime = (DDate) value;
			return dtime;
		}
		throw new XDataRuntimeException("Cannot get Date value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public DDateTime getDateTime() throws XDataRuntimeException {
		if(value == null) { 
			return null;
		}
		if (value instanceof DDateTime) {
			DDateTime dateTime = (DDateTime) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Cannot get DateTime value data type is " + DataTypes.getLiteral(this.getDataType()));
	}

	@Override
	public int getDataType() {
		return type.getDataType();
	}

	@Override
	public void setValue(Serializable value) throws XDataRuntimeException {
		if(!DataTypes.isDataType(value)) { 
			throw new XDataRuntimeException("Value Type " + value.getClass().getName() + " is not a valid attribute data type");
		}
		int dataType = DataTypes.getDataType(value);
		if(dataType != type.getDataType()) { 
			throw new XDataRuntimeException("Attribute " + getName() + " Type " + DataTypes.getLiteral(type.getDataType()) + " is trying to set value of data type  " + DataTypes.getLiteral(DataTypes.getDataType(value)));
		}
		this.value = value; 
		notifyObservers(this, XEvents.AttributeUpdate);
	}

	@Override
	public XAttributeType getType() {
		return type; 
	}

	@Override
	public void validate() throws XValidationException {
		for (XPropertyValidator validator : type.getValidators()) {
			validator.validate(this);
		}
	}

	
	
	
	

}
