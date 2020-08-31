package com.dunkware.xdata.bean;

import java.io.Serializable;
import java.util.Map;

import com.dunkware.commons.util.dtime.DDate;
import com.dunkware.commons.util.dtime.DDateTime;
import com.dunkware.commons.util.dtime.DTime;
import com.dunkware.commons.util.observable.DObservable;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.list.XList;
import com.dunkware.xdata.property.XAttribute;
import com.dunkware.xdata.property.XBeanRef;
import com.dunkware.xdata.property.XListRef;
import com.dunkware.xdata.property.XProperty;
import com.dunkware.xdata.validation.XValidationException;

public interface XBean extends DObservable {
	
	XBeanType getType();
	
	String getUUID();
	
	Map<String,XProperty> getProperties();
	
	boolean isNull(String property) throws XDataRuntimeException;
	
	boolean getBoolean(String attribute) throws XDataRuntimeException;
	
	void setBoolean(String bute, boolean value) throws XDataRuntimeException;
	
	XAttribute getAttribute(String attribute) throws XDataRuntimeException;
	
	Serializable getValue(String attribute) throws XDataRuntimeException;
	
	String getString(String attribute) throws XDataRuntimeException;
	
	public DDate getDate(String attribute) throws XDataRuntimeException;
	
	public void setDate(String attribute, DDate value) throws XDataRuntimeException;
	
	public void setString(String attribute, String value) throws XDataRuntimeException;
	
	public void setInt(String attribute, Integer value) throws XDataRuntimeException;
	
	public void setTime(String attribute, DTime value) throws XDataRuntimeException;
	
	public void setDouble(String attribute, Double value) throws XDataRuntimeException;
	
	Double getDouble(String attribute) throws XDataRuntimeException;
	
	DTime getTime(String attribute) throws XDataRuntimeException;
	
	DDateTime getDateTime(String attribute) throws XDataRuntimeException;
	
	void setDateTime(String attribute, DDateTime dateTime) throws XDataRuntimeException;
	
	Integer getInt(String attribute) throws XDataRuntimeException;
	
	void setValue(String property, Serializable value) throws XDataRuntimeException;
	
	XBeanRef getBeanRef(String name) throws XDataRuntimeException;
	
	XBean getBean(String name) throws XDataRuntimeException;
	
	XListRef getListRef(String name) throws XDataRuntimeException;
	
	public void setByteArray(String attribute, byte[] byteArray);
	
	public byte[] getByteArray(String name) throws XDataRuntimeException;
	
	XList getList(String name) throws XDataRuntimeException;
	
	void setBean(String property,XBean bean) throws XDataRuntimeException;
	
	void setList(String property, XList list) throws XDataRuntimeException;
	
	void validate() throws XValidationException;

	boolean hasAttribute(String name);
	
	XProperty getProperty(String name) throws XDataRuntimeException;
	
	public XBeanType getListType(String listName) throws XDataRuntimeException;
	
	
	
}
