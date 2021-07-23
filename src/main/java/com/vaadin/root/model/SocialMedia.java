package com.vaadin.root.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the social_media database table.
 * 
 */
@Entity
@Table(name="social_media")
@NamedQuery(name="SocialMedia.findAll", query="SELECT s FROM SocialMedia s")
public class SocialMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="sm_facebook")
	private String smFacebook;

	@Id
	@Column(name="sm_id")
	private Long smId;

	@Column(name="sm_instagram")
	private String smInstagram;

	@Column(name="sm_pinterest")
	private String smPinterest;

	@Column(name="sm_snapchat")
	private String smSnapchat;

	@Column(name="sm_tumblr")
	private String smTumblr;

	@Column(name="sm_twitter")
	private String smTwitter;

	@Column(name="sm_spotify")
	private String smSpotify;

	@Column(name="sm_applemusic")
	private String smApplemusic;
	
	public SocialMedia() {
	}

	public String getSmFacebook() {
		return this.smFacebook;
	}

	public void setSmFacebook(String smFacebook) {
		this.smFacebook = smFacebook;
	}

	public Long getSmId() {
		return this.smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public String getSmInstagram() {
		return this.smInstagram;
	}

	public void setSmInstagram(String smInstagram) {
		this.smInstagram = smInstagram;
	}

	public String getSmPinterest() {
		return this.smPinterest;
	}

	public void setSmPinterest(String smPinterest) {
		this.smPinterest = smPinterest;
	}

	public String getSmSnapchat() {
		return this.smSnapchat;
	}

	public void setSmSnapchat(String smSnapchat) {
		this.smSnapchat = smSnapchat;
	}

	public String getSmTumblr() {
		return this.smTumblr;
	}

	public void setSmTumblr(String smTumblr) {
		this.smTumblr = smTumblr;
	}

	public String getSmTwitter() {
		return this.smTwitter;
	}

	public void setSmTwitter(String smTwitter) {
		this.smTwitter = smTwitter;
	}

	public String getSmSpotify() {
		return smSpotify;
	}

	public void setSmSpotify(String smSpotify) {
		this.smSpotify = smSpotify;
	}

	public String getSmApplemusic() {
		return smApplemusic;
	}

	public void setSmApplemusic(String smApplemusic) {
		this.smApplemusic = smApplemusic;
	}
	
	

}