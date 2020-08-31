package com.dunkware.xdata.builders;

import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.property.XPropertyType;
import com.dunkware.xdata.property.impl.XAttributeTypeImpl;
import com.dunkware.xdata.property.impl.XBeanRefTypeImpl;
import com.dunkware.xdata.property.impl.XBeanTypeImpl;
import com.dunkware.xdata.property.impl.XListRefTypeImpl;
import com.dunkware.xdata.validation.XPropertyValidator;

public class XBeanTypeBuilder {

	public static XBeanTypeBuilder newInstance(String name, String namespace) {
		return new XBeanTypeBuilder(name, namespace);
	}

	private String name;
	private String namespace;

	ConcurrentHashMap<String, XPropertyType> props = new ConcurrentHashMap<String, XPropertyType>();

	private XBeanTypeBuilder(String name, String namespace) {
		this.name = name;
		this.namespace = namespace;
	}

	public XBeanTypeBuilder atttributeType(String name, String label, int dataType, String format,
			XPropertyValidator... validators) throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}

		XAttributeTypeImpl impl = new XAttributeTypeImpl(name, label, dataType, format);
		for (XPropertyValidator validator : validators) {
			impl.addValidator(validator);
		}

		props.put(name, impl);
		return this;
	}

	/**
	 * Create an attribute with a default "*" for format and no validators.
	 * 
	 * @param name
	 * @param label
	 * @param dataType
	 * @return
	 * @throws XDataRuntimeException
	 */
	public XBeanTypeBuilder atttributeType(String name, String label, int dataType) throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}

		XAttributeTypeImpl impl = new XAttributeTypeImpl(name, label, dataType, "*");
		props.put(name, impl);
		return this;
	}

	public XBeanTypeBuilder beanType(String name, String label, XBeanType type, XPropertyValidator... validators)
			throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}

		XBeanRefTypeImpl impl = new XBeanRefTypeImpl(name, label, type);
		for (XPropertyValidator validator : validators) {
			impl.addValidator(validator);
		}
		props.put(name, impl);
		return this;
	}

	public XBeanTypeBuilder beanType(String name, String label, XBeanType type) throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}

		XBeanRefTypeImpl impl = new XBeanRefTypeImpl(name, label, type);

		props.put(name, impl);
		return this;
	}

	public XBeanTypeBuilder listType(String name, String label, XBeanType type, XPropertyValidator... validators)
			throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}

		XListRefTypeImpl impl = new XListRefTypeImpl(name, label, type);
		for (XPropertyValidator validator : validators) {
			impl.addValidator(validator);
		}

		props.put(name, impl);
		return this;

	}

	public XBeanTypeBuilder listType(String name, String label, XBeanType type) throws XDataRuntimeException {
		if (props.contains(name)) {
			throw new XDataRuntimeException("Property name " + name + " already exists");
		}
	
		XListRefTypeImpl impl = new XListRefTypeImpl(name, label,type);
	props.put(name, impl);
		return this;

	}

	public XBeanType build() {
		XBeanTypeImpl impl = new XBeanTypeImpl(name, namespace,
				props.values().toArray(new XPropertyType[props.values().size()]));
		return impl;
	}

}
