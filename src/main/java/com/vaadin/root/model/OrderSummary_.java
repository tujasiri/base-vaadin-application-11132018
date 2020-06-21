package com.vaadin.root.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-06-21T13:13:13.857-0500")
@StaticMetamodel(OrderSummary.class)
public class OrderSummary_ {
	public static volatile SingularAttribute<OrderSummary, Long> orSeq;
	public static volatile SingularAttribute<OrderSummary, String> icColor;
	public static volatile SingularAttribute<OrderSummary, String> icGender;
	public static volatile SingularAttribute<OrderSummary, Integer> icId;
	public static volatile SingularAttribute<OrderSummary, Integer> icMtItemNum;
	public static volatile SingularAttribute<OrderSummary, String> icSize;
	public static volatile SingularAttribute<OrderSummary, Boolean> mtCustomizeable;
	public static volatile SingularAttribute<OrderSummary, Long> mtIcId;
	public static volatile SingularAttribute<OrderSummary, byte[]> mtImage;
	public static volatile SingularAttribute<OrderSummary, String> mtImageId;
	public static volatile SingularAttribute<OrderSummary, String> mtItemDescLong;
	public static volatile SingularAttribute<OrderSummary, String> mtItemDescShort;
	public static volatile SingularAttribute<OrderSummary, String> mtItemLink;
	public static volatile SingularAttribute<OrderSummary, Integer> mtItemNum;
	public static volatile SingularAttribute<OrderSummary, Float> mtItemPrice;
	public static volatile SingularAttribute<OrderSummary, String> mtItemType;
	public static volatile SingularAttribute<OrderSummary, Long> mtOrSeq;
	public static volatile SingularAttribute<OrderSummary, Integer> mtOrderId;
	public static volatile SingularAttribute<OrderSummary, String> mtSpecialNote;
	public static volatile SingularAttribute<OrderSummary, Short> mtStockQty;
	public static volatile SingularAttribute<OrderSummary, Integer> orCbId;
	public static volatile SingularAttribute<OrderSummary, Date> orDate;
	public static volatile SingularAttribute<OrderSummary, Integer> orIcId;
	public static volatile SingularAttribute<OrderSummary, Integer> orId;
	public static volatile SingularAttribute<OrderSummary, Integer> orMtItemNum;
}
