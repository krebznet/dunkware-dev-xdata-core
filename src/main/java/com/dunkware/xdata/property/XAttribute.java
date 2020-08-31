package com.dunkware.xdata.property;

import java.io.Serializable;
import java.util.Map;

import com.dunkware.commons.util.dtime.DDate;
import com.dunkware.commons.util.dtime.DDateTime;
import com.dunkware.commons.util.dtime.DTime;
import com.dunkware.commons.util.observable.DObservable;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.validation.XValidationException;

public interface XAttribute extends XProperty, DObservable  {

	Serializable getValue();
	
	boolean isNull();
	
	void setValue(Serializable value) throws XDataRuntimeException;
	
	XAttributeType getType();

	String getString() throws XDataRuntimeException;
	
	Double getDouble() throws XDataRuntimeException;
	
	Integer getInteger() throws XDataRuntimeException;
	
	DTime getTime() throws XDataRuntimeException;
	
	DDate getDate() throws XDataRuntimeException;
	
	DDateTime getDateTime() throws XDataRuntimeException;
	
	int getDataType();
	
	void validate() throws XValidationException;
	
}
