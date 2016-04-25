package com.cfang.WeChat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private Date createTime = new Date();
	private Date updatetime = new Date();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createtime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean flag = false;  
        if(obj != null || obj instanceof BaseEntity){ 
        	BaseEntity be = (BaseEntity)obj;  
            if(be.getId() == this.getId()){  
               flag = true;  
            }  
        }  
        return flag;
	}

	@Override
	public int hashCode(){
		return (int)(getId() ^ (getId() >>> 32));//copied from JAVA Long.hashCode()
	}
}
