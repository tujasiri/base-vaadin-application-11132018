package com.vaadin.root.model;

import com.vaadin.root.enums.MerchTypes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-07T18:34:35.889-0500")
@StaticMetamodel(RColor.class)
public class RColor_ {
	public static volatile SingularAttribute<RColor, Integer> id;
	public static volatile SingularAttribute<RColor, String> display;
	public static volatile SingularAttribute<RColor, String> value;
	public static volatile SingularAttribute<RColor, MerchTypes> merch_type;
}
