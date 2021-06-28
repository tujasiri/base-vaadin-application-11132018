package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the viewer_images database table.
 * 
 */
@Entity
@Table(name="viewer_images")
@NamedQueries(value = { 
@NamedQuery(name="ViewerImage.findAll", query="SELECT v FROM ViewerImage v"),
@NamedQuery(name="ViewerImage.findById", 
			query="SELECT v "
					+ "FROM ViewerImage v "
					+ "WHERE v.viSeq = :idx")
})
public class ViewerImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vi_seq")
	private String viSeq;

	@Lob
	@Column(name="vi_base64_str", nullable=false)
	private String viBase64Str;

	@Column(name="vi_mt_item_num", nullable=false)
	private int viMtItemNum;

	public ViewerImage() {
	}

	public String getViSeq() {
		return this.viSeq;
	}

	public void setViSeq(String viSeq) {
		this.viSeq = viSeq;
	}

	public String getViBase64Str() {
		return this.viBase64Str;
	}

	public void setViBase64Str(String viBase64Str) {
		this.viBase64Str = viBase64Str;
	}

	public int getViMtItemNum() {
		return this.viMtItemNum;
	}

	public void setViMtItemNum(int viMtItemNum) {
		this.viMtItemNum = viMtItemNum;
	}

}