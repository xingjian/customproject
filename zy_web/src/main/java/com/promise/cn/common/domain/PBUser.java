/*@文件名: PbUser.java  @创建人: 邢健   @创建日期: 2011-11-14 下午09:54:31*/
package com.promise.cn.common.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @类名: PBUser.java 
 * @包名: com.promise.cn.common.domain 
 * @描述: 用户类 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-11-14 下午09:54:31 
 * @版本 V1.0   
 */
@NamedQueries({
	@NamedQuery(name="listPBUser",query="select t from PBUser t")
})
@SuppressWarnings("all")
@Entity
@Table(name="pb_user")
public class PBUser implements Serializable{

	private String id;
	private String name;
	private String password;
	private String email;
	private List<Menu> menus;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	 @ManyToMany(targetEntity=Menu.class,fetch=FetchType.EAGER)
     @JoinTable(name="pbuser_menu",joinColumns={@JoinColumn(name="pbuserid",unique=true)},inverseJoinColumns={@JoinColumn(name="menuid")})
	public List<Menu> getMenus() {
		return menus;
	}
	 
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
