package com.dunkware.xdata.list.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.dunkware.commons.util.observable.DObservable;
import com.dunkware.commons.util.observable.DObservableImpl;
import com.dunkware.commons.util.observable.DObserver;
import com.dunkware.xdata.XEvents;
import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.exceptions.XDataRuntimeException;
import com.dunkware.xdata.list.XList;

public class XListImpl extends DObservableImpl implements XList {

	private XBeanType beanType; 
	private List<XBean> sourceList = new ArrayList<XBean>();
	private Semaphore sourceListLock = new Semaphore(1);
	
	private ListBeanObserver beanObserver = new ListBeanObserver();
	
	public XListImpl(XBeanType type) {
		this.beanType = type;			
	}
	
	
	public XListImpl(XBeanType type, List<XBean> sourceList) {
		this.beanType = type;
		this.sourceList = sourceList;
	}
	
	@Override
	public XBeanType getType() {
		return beanType;
	}

	@Override
	public void add(XBean bean) throws XDataRuntimeException {
		try {
			sourceListLock.acquire();
			sourceList.add(bean);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { 
			sourceListLock.release();
		}
		
		bean.addObserver(beanObserver);
		notifyObservers(this, XEvents.ListInsert);
	}

	@Override
	public void remove(XBean bean) throws XDataRuntimeException {
		if(sourceList.contains(bean) == true) { 
			try {
				sourceListLock.acquire();
				sourceList.remove(bean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally { 
				sourceListLock.release();
			}
			bean.deleteObserver(beanObserver);
		}

	}

	@Override
	public List<XBean> getElements() {
		return sourceList;
	}
	
	
	private class ListBeanObserver implements DObserver {

		@Override
		public void update(DObservable o, Object arg) {
			notifyObservers(o, arg);
		}
		
		
	}
	
	
	

}
