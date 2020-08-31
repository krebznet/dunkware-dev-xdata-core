package com.dunkware.xdata.property;

import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.list.XList;

public interface XListRef extends XProperty {

	XList getValue();
	
	void setValue(XList list) throws XDataRuntimeException;
	
	XListRefType getType();
	
}
