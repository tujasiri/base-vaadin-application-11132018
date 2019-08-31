package com.vaadin.root.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-08-11T01:23:02.518-0500")
@StaticMetamodel(MerchTable.class)
public class MerchTable_ {
	public static volatile SingularAttribute<MerchTable, Integer> mtItemNum;
	public static volatile SingularAttribute<MerchTable, Integer> mtIcId;
	public static volatile SingularAttribute<MerchTable, byte[]> mtImage;
	public static volatile SingularAttribute<MerchTable, String> mtImageId;
	public static volatile SingularAttribute<MerchTable, String> mtItemDescLong;
	public static volatile SingularAttribute<MerchTable, String> mtItemDescShort;
	public static volatile SingularAttribute<MerchTable, String> mtItemLink;
	public static volatile SingularAttribute<MerchTable, Float> mtItemPrice;
	public static volatile SingularAttribute<MerchTable, String> mtItemType;
	public static volatile SingularAttribute<MerchTable, Integer> mtOrderId;
	public static volatile SingularAttribute<MerchTable, String> mtSpecialNote;
	public static volatile SingularAttribute<MerchTable, Short> mtStockQty;
}
