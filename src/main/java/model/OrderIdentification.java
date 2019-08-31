package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_identification database table.
 * 
 */
@Entity
@Table(name="order_identification")
@NamedQuery(name="OrderIdentification.findAll", query="SELECT o FROM OrderIdentification o")
public class OrderIdentification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="or_id")
	private int orId;

	public OrderIdentification() {
	}

	public int getOrId() {
		return this.orId;
	}

	public void setOrId(int orId) {
		this.orId = orId;
	}

}