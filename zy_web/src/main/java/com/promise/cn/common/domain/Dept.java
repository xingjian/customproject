/** @文件名: Dept.java @创建人：邢健  @创建日期： 2013-10-14 上午10:39:50 */
package com.promise.cn.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @类名: Dept.java 
 * @包名: com.promise.cn.common.domain 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2013-10-14 上午10:39:50 
 * @版本: V1.0   
 */
@SuppressWarnings("all")		
@Entity
@Table(name = "dept")
public class Dept {

	private String id;
	private String code;
	private String name;
	private String createTime;
	private Dept parent;
	public List<Dept> children = new ArrayList<Dept>();
	private String leaf;// 0表示不是叶子 1表示叶子
	private boolean state;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(targetEntity = Dept.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	@Transient
	public List<Dept> getChildren() {
		return children;
	}
	public void setChildren(List<Dept> depts) {
		this.children = depts;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	@Transient
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
	
}
