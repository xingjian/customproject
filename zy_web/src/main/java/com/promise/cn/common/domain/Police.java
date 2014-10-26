/** @文件名: Police.java @创建人：邢健  @创建日期： 2013-10-14 上午11:06:01 */
package com.promise.cn.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @类名: Police.java 
 * @包名: com.promise.cn.common.domain 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2013-10-14 上午11:06:01 
 * @版本: V1.0   
 */
@SuppressWarnings("all")		
@Entity
@Table(name = "police")
public class Police {

	private String id;
	private String name;
	private String code;
	private String phone;
	private String createDate;
	private Dept dept;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(targetEntity = Dept.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "deptid")
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
