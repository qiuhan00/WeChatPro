package com.cfang.WeChat.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_operator_resource")
public class OperatorResource extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String resourceName;//资源名称
	
	private String description;//资源描述
	
	private String resourceURL;//资源url
	
	private String status;//资源状态
	
	private int index = 0; // 资源排序位置
	
	private OperatorResource parent;//父节点
	
	private List<OperatorResource> childs;//子节点
	
	private List<Role> roles;//权限组

	@Column(name="resourceName")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="resourceURL")
	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="index")
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	public OperatorResource getParent() {
		return parent;
	}

	public void setParent(OperatorResource parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public List<OperatorResource> getChilds() {
		return childs;
	}

	public void setChilds(List<OperatorResource> childs) {
		this.childs = childs;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="t_role2resource", joinColumns={@JoinColumn(name="resource_code")}, inverseJoinColumns={@JoinColumn(name="role_code")})
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/***
	 * 加载已排序的子菜单列表. loadSortedChildren:
	 * @return
	 */
	public List<OperatorResource> loadSortedChildren() {
		List<OperatorResource> subList = new ArrayList<OperatorResource>();
		if (childs != null && !childs.isEmpty()) {
			for (OperatorResource node : childs) {
				int offset = 0;
				for (offset = 0; offset < subList.size(); offset++) {
					if (node.getIndex() < subList.get(offset).getIndex()) {
						break;
					}
				}
				subList.add(offset, node);
			}
		}
		return subList;
	}
}
