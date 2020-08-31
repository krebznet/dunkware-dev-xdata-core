package com.dunkware.xdata.tree;

public interface XTree {

	XTreeNode[] getNodes();
	
	void visit(XTreeVisitor visitor);
	
}
