package com.cfang.WeChat.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_user")
public class User extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String passWord;
	private String openId;
	private List<Role> roleList;//一个用户具有多个角色
	@Column(name="username", nullable=false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password", nullable=false)
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Column(name="openid", nullable=false)
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="t_user_role", joinColumns={@JoinColumn(name="user_id")},
				inverseJoinColumns={@JoinColumn(name="role_id")})
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	@Transient
	public Set<String> getRoles() {
		List<Role> roles=getRoleList();  
        Set<String> set=new HashSet<String>();  
        for (Role role : roles) {  
            set.add(role.getRolecode());  
        }  
        return set; 
	}
}
