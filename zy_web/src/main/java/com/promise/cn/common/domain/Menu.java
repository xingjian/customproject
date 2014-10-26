/*@文件名: Menu.java  @创建人: 邢健   @创建日期: 2011-12-14 上午10:40:42*/
package com.promise.cn.common.domain;

import java.io.Serializable;
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
 * @类名: Menu.java 
 * @包名: com.promise.cn.common.domain 
 * @描述: 菜单 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-14 上午10:40:42 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
@Entity
@Table(name="menu")
public class Menu implements Serializable {

	private String id;
	
	private String name;//名称
	
	private Menu parent;//父节点
	
	private String url;//url 唯一
	
	private String remark;//备注

	private String sort;//排序
	
	public List<Menu> children;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Menu.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Transient
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	
	@Transient
	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
