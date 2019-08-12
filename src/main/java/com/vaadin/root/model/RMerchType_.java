package com.vaadin.root.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-08-07T12:01:33.851-0500")
@StaticMetamodel(RMerchType.class)
public class RMerchType_ {
	public static volatile SingularAttribute<RMerchType, Integer> id;
	public static volatile SingularAttribute<RMerchType, String> display;
	public static volatile SingularAttribute<RMerchType, String> value;
	public static volatile ListAttribute<RMerchType, RColor> RColors;
}
