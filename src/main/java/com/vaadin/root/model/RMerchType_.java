package com.vaadin.root.model;

import com.vaadin.root.enums.MerchTypes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-16T00:41:04.199-0600")
@StaticMetamodel(RMerchType.class)
public class RMerchType_ {
	public static volatile SingularAttribute<RMerchType, Integer> id;
	public static volatile SingularAttribute<RMerchType, String> display;
	public static volatile SingularAttribute<RMerchType, String> value;
	public static volatile SingularAttribute<RMerchType, MerchTypes> merch_type;
}
