package com.dunkware.xdata;

import com.dunkware.commons.util.observable.DObservableTopic;

public class XEvents {
	
	public static final DObservableTopic AttributeUpdate = new DObservableTopic("AttributeUpate");
	public static final DObservableTopic ListInsert = new DObservableTopic("ListInsert");
	public static final DObservableTopic ListRemove = new DObservableTopic("ListRemove");

}
