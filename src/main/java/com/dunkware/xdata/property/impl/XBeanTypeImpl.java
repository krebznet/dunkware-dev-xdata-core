package com.dunkware.xdata.property.impl;

import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.property.XPropertyType;

public class XBeanTypeImpl implements XBeanType {

	private String name;
	private String namespace;
	private XPropertyType[] properties;

	public XBeanTypeImpl(String name, String namespace, XPropertyType[] props) {
		this.name = name;
		this.namespace = namespace;
		this.properties = props;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public XPropertyType[] getProperties() {
		return properties;
	}

	@Override
	public XAttributeType getAttributeType(String name) throws XDataRuntimeException {
		XPropertyType type = null;
		for (XPropertyType xPropertyType : properties) {
			if (xPropertyType.getName().equals(name)) {
				type = xPropertyType;
				if (type instanceof XAttributeType) {
					XAttributeType buteType = (XAttributeType) type;
					return buteType;
				} else { 
					throw new XDataRuntimeException("Expected AttributeType but retrieved " + xPropertyType.getClass().getName());
				}
			}
		}
		throw new XDataRuntimeException("Attribute Type " + name + " not found in bean type");

	}

	@Override
	public XBeanRefType getBeanRefType(String name) throws XDataRuntimeException {
		XPropertyType type = null;
		for (XPropertyType xPropertyType : properties) {
			if (xPropertyType.getName().equals(name)) {
				type = xPropertyType;
				if (type instanceof XBeanRefType) {
					XBeanRefType buteType = (XBeanRefType) type;
					return buteType;
				} else { 
					throw new XDataRuntimeException("Expected XBeanRefType but retrieved " + xPropertyType.getClass().getName());
				}
			}
		}
		throw new XDataRuntimeException("BeanRefType " + name + " not found in bean type");
	}

	@Override
	public XListRefType getListRefType(String name) throws XDataRuntimeException {
		XPropertyType type = null;
		for (XPropertyType xPropertyType : properties) {
			if (xPropertyType.getName().equals(name)) {
				type = xPropertyType;
				if (type instanceof XListRefType) {
					XListRefType buteType = (XListRefType) type;
					return buteType;
				} else { 
					throw new XDataRuntimeException("Expected XListRefType but retrieved " + xPropertyType.getClass().getName());
				}
			}
		}
		throw new XDataRuntimeException("XListRefType " + name + " not found in bean type");
	}

}
