package com.dunkware.xdata.tree;

import com.dunkware.commons.util.observable.DObservable;

public interface XTreeNode extends DObservable {
	
	XTreeNode getParent();
	
	XTreeNode[] getChildren();
	
	boolean hasChildren();
	

}
