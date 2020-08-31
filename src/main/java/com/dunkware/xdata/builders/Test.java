package com.dunkware.xdata.builders;

import com.dunkware.commons.util.datatype.DataTypes;
import com.dunkware.xdata.bean.XBean;
import com.dunkware.xdata.bean.XBeanType;
import com.dunkware.xdata.bean.impl.XBeanImpl;
import com.dunkware.xdata.validation.validators.XNotNullValidator;

public class Test {

	public static void main(String[] args) {
		try {
			XBeanType type = XBeanTypeBuilder.newInstance("Subscription", "Ticknet")
					.atttributeType("ticker", "Ticker", DataTypes.STRING, "#",new XNotNullValidator())
					.atttributeType("instrument", "Instrument", DataTypes.STRING, "#", new XNotNullValidator())
					.build();
			XBean bean = new XBeanImpl(type);
			try {
				bean.setValue("ticker", "SPY");
				bean.setValue("Last", 32.3);
				bean.setValue("Volume", 323L);
				
				System.out.println(bean.getString("ticker"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				bean.validate();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
