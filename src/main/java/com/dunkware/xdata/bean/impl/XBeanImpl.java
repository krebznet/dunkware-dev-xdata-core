package com.dunkware.xdata.bean.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.commons.util.datatype.DataTypes;
import com.dunkware.commons.util.dtime.DDate;
import com.dunkware.commons.util.dtime.DDateTime;
import com.dunkware.commons.util.dtime.DTime;
import com.dunkware.commons.util.observable.DObservable;
import com.dunkware.commons.util.observable.DObservableImpl;
import com.dunkware.commons.util.observable.DObserver;
import com.dunkware.commons.util.uuid.DUUID;
import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
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
import com.dunkware.xdata.validation.XValidationException;

public class XBeanImpl extends DObservableImpl implements XBean, DObserver {
	
	private XBeanType type; 
	private String UUID; 
	private ConcurrentHashMap<String, XProperty> properties = new ConcurrentHashMap<String,XProperty>();
	private ConcurrentHashMap<String,Object> metadata = new ConcurrentHashMap<String,Object>();

	public XBeanImpl(XBeanType type) { 
		UUID = DUUID.randomUUID(5);
		this.type = type;
		for (XPropertyType propType: type.getProperties()) {
			if (propType instanceof XAttributeType) {
				XAttributeType buteType = (XAttributeType) propType;
				XAttributeImpl bute = new XAttributeImpl(buteType);
				properties.put(propType.getName(), bute);
			}
			if (propType instanceof XBeanRefType) {
				XBeanRefType refType = (XBeanRefType) propType;
				XBeanRefImpl ref = new XBeanRefImpl(refType);
				properties.put(refType.getName(), ref);
			}
			if (propType instanceof XListRefType) {
				XListRefType ref = (XListRefType) propType;
				XListRefImpl impl = new XListRefImpl(ref);
				properties.put(ref.getName(), impl);
			}
		}
	}
	
	public XBeanImpl(XBeanType type, String UUID, ConcurrentHashMap<String,XProperty> props) {
		this.type = type;
		this.properties = props;
		this.UUID = UUID;
		// set container on attributes and add observer
		for (XProperty prop : properties.values()) {
			if (prop instanceof XAttribute) {
				XAttribute bute = (XAttribute) prop;
				bute.addObserver(this);
			}
		}
	}

	@Override
	public XBeanType getType() {
		return type; 
	}

	@Override
	public String getUUID() {
		return UUID; 
	}

	@Override
	public Map<String,XProperty> getProperties() {
		return properties;
	}

	@Override
	public XAttribute getAttribute(String name) throws XDataRuntimeException {
		if(!properties.keySet().contains(name)) { 
			throw new XDataRuntimeException("Attribute " + name + " not found");
		}
		XProperty prop = properties.get(name);
		if (prop instanceof XAttribute) {
			XAttribute bute = (XAttribute) prop;
			return bute;
		} else { 
			throw new XDataRuntimeException("Property " + name + " is not attribute property but " + prop.getClass().getName());
		}
	}

	@Override
	public Serializable getValue(String name) throws XDataRuntimeException {
		return getAttribute(name).getValue();
		
	}

	@Override
	public void setValue(String bute, Serializable value) throws XDataRuntimeException {
		XAttribute attribute = getAttribute(bute);
		attribute.setValue(value);
	}

	@Override
	public XBeanRef getBeanRef(String name) throws XDataRuntimeException {
		if(!properties.contains(name)) { 
			throw new XDataRuntimeException("Bean Ref " + name + " not found");
		}
		XProperty prop = properties.get(name);
		if (prop instanceof XBeanRef) {
			XBeanRef bute = (XBeanRef) prop;
			return bute;
		} else { 
			throw new XDataRuntimeException("Property " + name + " is not beanref property but " + prop.getClass().getName());
		}
		
	}

	@Override
	public XBean getBean(String name) throws XDataRuntimeException {
		return getBeanRef(name).getValue();
	}

	@Override
	public XListRef getListRef(String name) throws XDataRuntimeException {
		if(!properties.containsKey(name)) { 
			throw new XDataRuntimeException("List Ref " + name + " not found");
		}
		XProperty prop = properties.get(name);
		if (prop instanceof XListRef) {
			XListRef bute = (XListRef) prop;
			return bute;
		} else { 
			throw new XDataRuntimeException("Property " + name + " is not list ref property but " + prop.getClass().getName());
		}
	}

	@Override
	public XList getList(String name) throws XDataRuntimeException {
		return getListRef(name).getValue();
	}

	@Override
	public void update(DObservable o, Object arg) {
		notifyObservers(o, arg);
	}

	@Override
	public void validate() throws XValidationException {
		for (XProperty property : properties.values()) {
			property.validate();
		}
	}
	
	

	@Override
	public boolean getBoolean(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof Boolean) {
			Boolean boolValue = (Boolean) value;
			return boolValue;
		}
		throw new XDataRuntimeException("Attribute type is not boolean but " + value.getClass().getName());
	}

	@Override
	public String getString(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof String) {
			String dateTime = (String) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Attribute " + attribute + " is not type String");
	}

	@Override
	public Double getDouble(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof Double) {
			Double dateTime = (Double) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Attribute " + attribute + " is not type Double");
	}

	@Override
	public DTime getTime(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof DTime) {
			DTime dateTime = (DTime) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Attribute " + attribute + " is not type Time");
	}

	@Override
	public DDateTime getDateTime(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof DDateTime) {
			DDateTime dateTime = (DDateTime) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Attribute " + attribute + " is not type DateTime");
	}

	@Override
	public DDate getDate(String attribute) throws XDataRuntimeException {
		if(!hasAttribute(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " does not exist on bean type " + type.getName());
		if(isNull(attribute))
			throw new XDataRuntimeException("Attribute " + attribute + " is null");
		Serializable value =  getValue(attribute);
		if (value instanceof DDate) {
			DDate dateTime = (DDate) value;
			return dateTime;
		}
		throw new XDataRuntimeException("Attribute " + attribute + " is not type Date");
	}
	
	

	public void setDate(String attribute, DDate value) throws XDataRuntimeException { 
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.DATE) {
			throw new XDataRuntimeException("Invalid Date Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}
	
	
	
	
	@Override
	public void setDateTime(String attribute, DDateTime dateTime) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.DATETIME) {
			throw new XDataRuntimeException("Invalid DateTime Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(dateTime);
	}

	@Override
	public void setString(String attribute, String value) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.STRING) {
			throw new XDataRuntimeException("Invalid String Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}

	@Override
	public void setInt(String attribute, Integer value) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.INTEGER) {
			throw new XDataRuntimeException("Invalid Int Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}

	@Override
	public void setTime(String attribute, DTime value) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.TIME) {
			throw new XDataRuntimeException("Invalid Time Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}
	
	

	@Override
	public void setBoolean(String attribute, boolean value) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.BOOLEAN) { 
			throw new XDataRuntimeException("Cannot set boolean value on attribute " + attribute + " with data type  " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}

	@Override
	public void setDouble(String attribute, Double value) throws XDataRuntimeException {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.DOUBLE) {
			throw new XDataRuntimeException("Invalid Double Setter on attribute " + bute.getName() + " data type " + DataTypes.getLiteral(bute.getDataType()));
		}
		bute.setValue(value);
	}

	@Override
	public Integer getInt(String attribute) throws XDataRuntimeException {
		Serializable serializable = getValue(attribute);
		if(serializable == null) { 
			return null;
		}
		if (serializable instanceof Integer) {
			Integer integer = (Integer) serializable;
			return integer;
		}
		throw new XDataRuntimeException("Attribute Value is not Integer Type but " + serializable.getClass().getName());
	}
	
	
	

	@Override
	public void setByteArray(String attribute, byte[] byteArray) {
		XAttribute bute = getAttribute(attribute);
		if(bute.getDataType() != DataTypes.BYTEARRAY) { 
			throw new XDataRuntimeException("Attribute " + attribute + " is not type byte array");
		}
		bute.setValue(byteArray);
	}

	@Override
	public byte[] getByteArray(String name) throws XDataRuntimeException {
		XAttribute bute = getAttribute(name);
		Serializable value = bute.getValue();
		if(value == null) { 
			return null;
		}
		if (value instanceof byte[]) {
			byte[] bytes = (byte[]) value;
			return bytes;
		}
		throw new XDataRuntimeException("Expected byte array value for property " + name + " but returned " + value.getClass().getName());
	}

	@Override
	public boolean isNull(String property) throws XDataRuntimeException {
		XProperty prop = properties.get(property);
		if(prop == null) { 
			throw new XDataRuntimeException("Property " + property + " not found");
		}
		return prop.isNull();
	}

	@Override
	public boolean hasAttribute(String name) {
		XProperty prop = properties.get(name);
		if(prop == null) { 
			return false; 
		}
		if (prop instanceof XAttribute) {
			XAttribute bute = (XAttribute) prop;
			return true; 
		}
		return false;
	}
	
	

	@Override
	public XProperty getProperty(String name) throws XDataRuntimeException {
		XProperty prop = properties.get(name);
		if(prop == null) { 
			throw new XDataRuntimeException("Property " + name + " does not exist");
		}
		return prop;
	}

	@Override
	public void setBean(String property, XBean bean) throws XDataRuntimeException {
		XProperty prop = getProperty(property);
		if (prop instanceof XBeanRef) {
			XBeanRef beanRef = (XBeanRef) prop;
			beanRef.setValue(bean);
			return;
		}
		throw new XDataRuntimeException("Setting bean on invalid property type " + property);
	}

	@Override
	public void setList(String property, XList list) throws XDataRuntimeException {
		XProperty prop = getProperty(property);
		if (prop instanceof XListRef) {
			XListRef listRef = (XListRef) prop;
			listRef.setValue(list);
			return;
		}
		throw new XDataRuntimeException("Setting list on invalid property type " + property +  " type is " + prop.getClass().getName());
		
	}

	@Override
	public XBeanType getListType(String listName) throws XDataRuntimeException {
		for (XPropertyType type : getType().getProperties()) {
			if(type.getName().equals(listName)) {
				if (type instanceof XListRefType) {
					XListRefType listRef = (XListRefType) type;
					return listRef.getType();
				} 
				throw new XDataRuntimeException("property is not list ref type " + listName + " cannot get xbean type of list");
			}
		}
		throw new XDataRuntimeException("list type " + listName + " not found cannot get xbean type of list ref");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
