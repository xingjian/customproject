/*@文件名: DictContent.java  @创建人: 邢健   @创建日期: 2011-12-9 下午03:59:24*/
package com.promise.cn.common.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @类名: DictContent.java
 * @包名: com.promise.cn.common.domain
 * @描述: 字典表
 * @作者: 邢健 xingjian@dhcc.com.cn
 * @日期: 2011-12-9 下午03:59:24
 * @版本 V1.0
 */
@NamedQueries({
		@NamedQuery(name = "listDictContent", query = "select t from DictContent t where t.dict.code=? order by t.code asc"),
		@NamedQuery(name = "listAllDictContent", query = "select t from DictContent t"),
		@NamedQuery(name = "getDictContentByCodeOrName", query = "select t from DictContent t where t.dict.id=? and (t.code=? or t.name=?)"),
		@NamedQuery(name = "codeParseChinese", query = "select t from DictContent t where t.dict.code=? and t.code=?"),
})
@SuppressWarnings("all")
@Entity
@Table(name = "DictContent")
public class DictContent implements Serializable {
	private String id;
	private Dict dict;
	private String code;
	private String name;
	private DictContent parent;

	@ManyToOne(targetEntity = DictContent.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parentid")
	public DictContent getParent() {
		return this.parent;
	}

	public void setParent(DictContent parent) {
		this.parent = parent;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String dictContentId) {
		this.id = dictContentId;
	}

	@ManyToOne(targetEntity = Dict.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "dictid")
	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	@Basic
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Basic
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
