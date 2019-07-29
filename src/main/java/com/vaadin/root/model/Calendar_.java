package com.vaadin.root.model;

import java.sql.Time;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-16T17:06:31.977-0500")
@StaticMetamodel(Calendar.class)
public class Calendar_ {
	public static volatile SingularAttribute<Calendar, String> calAddress;
	public static volatile SingularAttribute<Calendar, String> calComments;
	public static volatile SingularAttribute<Calendar, Date> calDate;
	public static volatile SingularAttribute<Calendar, String> calEvent;
	public static volatile SingularAttribute<Calendar, byte[]> calFlyer;
	public static volatile SingularAttribute<Calendar, Integer> calId;
	public static volatile SingularAttribute<Calendar, String> calRsvp;
	public static volatile SingularAttribute<Calendar, Time> calTime;
	public static volatile SingularAttribute<Calendar, String> calVenue;
}
