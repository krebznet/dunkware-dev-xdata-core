package com.dunkware.xdata.util;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.property.XAttributeType;
import com.dunkware.xdata.property.XBeanRefType;
import com.dunkware.xdata.property.XListRefType;
import com.dunkware.xdata.property.XPropertyType;

public class XDataBeanTypeUtil {
	
	public static XAttributeType[] getAttributeTypes(XBeanType type) {
		List<XAttributeType> types = new ArrayList<XAttributeType>();
		for (XPropertyType prop : type.getProperties()) {
			if (prop instanceof XAttributeType) {
				XAttributeType buteType = (XAttributeType) prop;
				types.add(buteType);
			}
		}
		return types.toArray(new XAttributeType[types.size()]);
	}
	
	public static XBeanRefType[] getBeanRefTypes(XBeanType type) { 
		List<XBeanRefType> types = new ArrayList<XBeanRefType>();
		for (XPropertyType prop : type.getProperties()) {
			if (prop instanceof XBeanRefType) {
				XBeanRefType refType = (XBeanRefType) prop;
				types.add(refType);
			}
		}
		return types.toArray(new XBeanRefType[types.size()]);
	}

	public static XListRefType[] getListRefTypes(XBeanType type) { 
		List<XListRefType> types = new ArrayList<XListRefType>();
		for (XPropertyType prop : type.getProperties()) {
			if (prop instanceof XListRefType) {
				XListRefType refType = (XListRefType) prop;
				types.add(refType);
			}
		}
		return types.toArray(new XListRefType[types.size()]);
	}
}
