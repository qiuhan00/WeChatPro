package com.cfang.WeChat.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="t_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String rolename;
	
	private String rolecode;
	
	private String roledesc;
	
	private List<User> userList;//一个角色对应多个用户   
	
	private List<Permission> permissionList;
	
	private Set<OperatorResource> resources; //权限所拥有的资源
	
	@Column(name="rolename")
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name="rolecode")
	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Column(name="roledesc")
	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	@ManyToMany
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinTable(name="t_user_role",  //指定第三张表
			  	joinColumns={@JoinColumn(name="role_id")},//本表与第三张表关联关系 
				inverseJoinColumns={@JoinColumn(name="user_id")})  //另一张表与第三张表关联关系
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@OneToMany(mappedBy="role", fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	
	@Transient
	public List<String> getPermissions(){
		List<String> list=new ArrayList<String>();  
        List<Permission> perlist=getPermissionList();  
        for (Permission per : perlist) {  
            list.add(per.getPermcode());  
        }  
        return list;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="t_role2resource", joinColumns={@JoinColumn(name="role_code")}, inverseJoinColumns={@JoinColumn(name="resource_code")})
	public Set<OperatorResource> getResources() {
		return resources;
	}

	public void setResources(Set<OperatorResource> resources) {
		this.resources = resources;
	}

}
