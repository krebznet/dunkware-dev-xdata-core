package com.dunkware.xdata.list;

import java.util.List;

import com.dunkware.commons.util.observable.DObservable;
import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.exceptions.XDataRuntimeException;

public interface XList extends DObservable {
	
	XBeanType getType();
	
	void add(XBean bean) throws XDataRuntimeException;
	
	void remove(XBean bean) throws XDataRuntimeException;

	List<XBean> getElements();
}


