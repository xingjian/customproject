/*@文件名: Dict.java  @创建人: 邢健   @创建日期: 2011-12-9 下午03:56:17*/
package com.promise.cn.common.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * @类名: Dict.java
 * @包名: com.promise.cn.common.domain
 * @描述: 字典表
 * @作者: 邢健 xingjian@dhcc.com.cn
 * @日期: 2011-12-9 下午03:56:17
 * @版本 V1.0
 */
@NamedQueries({
		@NamedQuery(name = "listDict", query = "select t from Dict t"),
		@NamedQuery(name = "getDictByCodeOrName", query = "select t from Dict t where t.code=? or t.name=?") })

@SuppressWarnings("all")		
@Entity
@Table(name = "dict")
public class Dict implements Serializable {
	private String id;
	private String code;
	private String name;

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String dictId) {
		this.id = dictId;
	}

	@Basic
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
