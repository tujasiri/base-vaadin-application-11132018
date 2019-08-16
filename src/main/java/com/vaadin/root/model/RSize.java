package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the R_SIZES database table.
 * 
 */
@Entity
@Table(name="R_SIZES")
@NamedQueries(value = { @NamedQuery(name="RSize.findAll", query="SELECT r FROM RSize r"), 
						@NamedQuery(name="RSize.findAllSizeVals", query="SELECT r.value FROM RSize r")
})
public class RSize implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String display;

	private String value;

	public RSize() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}