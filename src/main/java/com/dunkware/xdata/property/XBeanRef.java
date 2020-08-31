package com.dunkware.xdata.property;

import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.exceptions.XDataRuntimeException;

public interface XBeanRef extends XProperty {
	
	XBean getValue();
	
	XBeanRefType getType();
	
	void setValue(XBean bean) throws XDataRuntimeException;
}
