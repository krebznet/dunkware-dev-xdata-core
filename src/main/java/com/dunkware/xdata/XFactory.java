package com.dunkware.xdata;

import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.builders.XBeanBuilder;
import com.dunkware.xdata.builders.XBeanTypeBuilder;

public class XFactory {

	public static XBean bean(XBeanType type) {
		return null;
	}
	
	public static XBeanTypeBuilder beanTypeBuilder(String name, String namespace) { 
		return XBeanTypeBuilder.newInstance(name, namespace);
	}
	
	public static XBeanBuilder beanBuilder(XBeanType beanType) { 
		return XBeanBuilder.newBuilder(beanType);
	}
	
	
}
