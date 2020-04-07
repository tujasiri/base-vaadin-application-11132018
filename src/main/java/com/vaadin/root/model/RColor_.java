package com.vaadin.root.model;

import com.vaadin.root.enums.MerchTypes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-16T00:51:00.220-0600")
@StaticMetamodel(RColor.class)
public class RColor_ {
	public static volatile SingularAttribute<RColor, Integer> id;
	public static volatile SingularAttribute<RColor, String> display;
	public static volatile SingularAttribute<RColor, MerchTypes> merch_type;
	public static volatile SingularAttribute<RColor, String> value;
}
