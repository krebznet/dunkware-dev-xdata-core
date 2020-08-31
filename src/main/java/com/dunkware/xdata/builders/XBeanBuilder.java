package com.dunkware.xdata.builders;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.commons.util.uuid.DUUID;
import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.bean.impl.XBeanImpl;
import com.dunkware.xdata.bean.impl.XBeanRefImpl;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.list.XList;
import com.dunkware.xdata.property.XAttribute;
import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.property.XBeanRef;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.property.XListRef;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.property.XProperty;
import com.dunkware.xdata.property.XPropertyType;
import com.dunkware.xdata.property.impl.XAttributeImpl;
import com.dunkware.xdata.property.impl.XListRefImpl;

public class XBeanBuilder {

	public static XBeanBuilder newBuilder(XBeanType type) {
		return new XBeanBuilder(type);
	}

	private XBeanType type;

	private ConcurrentHashMap<String, XProperty> properties = new ConcurrentHashMap<String, XProperty>();

	private XBeanBuilder(XBeanType type) {
		this.type = type;
		for (XPropertyType propType : type.getProperties()) {
			if (propType instanceof XAttributeType) {
				XAttributeType buteType = (XAttributeType) propType;
				XAttributeImpl bute = new XAttributeImpl(buteType);
				properties.put(buteType.getName(), bute);
			}
			if (propType instanceof XBeanRefType) {
				XBeanRefType beanRefType = (XBeanRefType) propType;
				XBeanRef beanRef = new XBeanRefImpl(beanRefType);
				properties.put(beanRefType.getName(), beanRef);
			}
			if (propType instanceof XListRefType) {
				XListRefType listRefType = (XListRefType) propType;
				XListRefImpl listRef = new XListRefImpl(listRefType);
				properties.put(listRefType.getName(), listRef);
			}
		}

	}

	public XBeanBuilder setValue(String bute, Serializable value) throws XDataRuntimeException {

		if (properties.get(bute) == null) {
			throw new XDataRuntimeException("Invalid Attribute Name " + bute);
		}
		XAttribute attribute = (XAttribute) properties.get(bute);
		try {
			attribute.setValue(value);
		} catch (Exception e) {
			throw new XDataRuntimeException("Invalid Attribute Value " + e.toString());
		}
		try {
			attribute.validate();
		} catch (Exception e) {
			throw new XDataRuntimeException("Validation Exception " + e.toString());
		}

		return this;
	}
	
	public XBeanBuilder setBean(String ref, XBean bean) throws XDataRuntimeException { 
		if(properties.get(ref) == null) { 
			throw new XDataRuntimeException("Bean Ref Property " + ref + " does not exist");
		}
		XBeanRef beanRef = (XBeanRef)properties.get(ref);
		try {
			beanRef.setValue(bean);
		} catch (Exception e) {
			throw new XDataRuntimeException("Invalid Bean Type " + e.toString());
		}
		return this; 
	}
	
	public XBeanBuilder setList(String ref, XList list) throws XDataRuntimeException { 
		if(properties.get(ref) == null) {
			throw new XDataRuntimeException("Bean List Ref " + ref + " does not exist");
		}
		XListRef listRef = (XListRef)properties.get(ref);
		try {
			listRef.setValue(list);
		} catch (Exception e) {
			throw new XDataRuntimeException("Invalid list set on list ref " + e.toString());
		}
		return this;
	}
	
	public XBean build() throws XDataRuntimeException { 
		XBean bean = new XBeanImpl(type,new DUUID().randomUUID(5), properties);
		try {
			bean.validate();
		} catch (Exception e) {
			throw new XDataRuntimeException("Bean Validation Exception " + e.toString());
		}
		return bean;
	}

}
