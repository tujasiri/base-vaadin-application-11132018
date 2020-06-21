package com.vaadin.root.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-08-09T17:35:18.774-0500")
@StaticMetamodel(MerchTableNew.class)
public class MerchTableNew_ {
	public static volatile SingularAttribute<MerchTableNew, Integer> mtItemNum;
	public static volatile SingularAttribute<MerchTableNew, Integer> mtIcId;
	public static volatile SingularAttribute<MerchTableNew, byte[]> mtImage;
	public static volatile SingularAttribute<MerchTableNew, String> mtImageId;
	public static volatile SingularAttribute<MerchTableNew, String> mtItemDescLong;
	public static volatile SingularAttribute<MerchTableNew, String> mtItemDescShort;
	public static volatile SingularAttribute<MerchTableNew, String> mtItemLink;
	public static volatile SingularAttribute<MerchTableNew, Float> mtItemPrice;
	public static volatile SingularAttribute<MerchTableNew, String> mtItemType;
	public static volatile SingularAttribute<MerchTableNew, Integer> mtOrderId;
	public static volatile SingularAttribute<MerchTableNew, String> mtSpecialNote;
	public static volatile SingularAttribute<MerchTableNew, Short> mtStockQty;
}
