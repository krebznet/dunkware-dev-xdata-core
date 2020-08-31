package com.dunkware.xdata.bean;

import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.property.XPropertyType;

/**
 * MetaData/Type class for a XBean. Describes the attributes, bean references
 * and bean lists it contains.
 * 
 * What about validators? 
 * @author dkrebs
 *
 */
public interface XBeanType {
	
	String getNamespace();
	
	String getName();

	XPropertyType[] getProperties(); 
	
	public XAttributeType getAttributeType(String name) throws XDataRuntimeException;
	
	public XBeanRefType getBeanRefType(String name) throws XDataRuntimeException;
	
	public XListRefType getListRefType(String name) throws XDataRuntimeException;
	

}
