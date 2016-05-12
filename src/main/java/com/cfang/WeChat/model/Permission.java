package com.cfang.WeChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_permission")
public class Permission extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String permname; //权限名称
	
	private String permcode; //权限代码
	
	private Role role;

	@Column(name="permname")
	public String getPermname() {
		return permname;
	}

	public void setPermname(String permname) {
		this.permname = permname;
	}

	@Column(name="permcode")
	public String getPermcode() {
		return permcode;
	}

	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}

	@ManyToOne
	@JoinColumn(name="roleid")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
