package com.cfang.WeChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_access_token")
public class AccessToken extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String openId;
	
	private String name;
	
	private String accessToken;
	
	private String userHeadImage;

	@Column(name="openid", nullable=false)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="accessToken", nullable=false)
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name="userHeadImage")
	public String getUserHeadImage() {
		return userHeadImage;
	}

	public void setUserHeadImage(String userHeadImage) {
		this.userHeadImage = userHeadImage;
	}
	
	

}
