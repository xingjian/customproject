/** @文件名: DeptService.java @创建人：邢健  @创建日期： 2013-10-14 上午10:43:29 */
package com.promise.cn.common.service;

import java.util.List;

import com.promise.cn.common.domain.Dept;

/**   
 * @类名: DeptService.java 
 * @包名: com.promise.cn.common.service 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2013-10-14 上午10:43:29 
 * @版本: V1.0   
 */
public interface DeptService {
	public String saveDept(Dept parent,Dept dept);
	public String editDept(Dept dept);
	public String deleteDept(String id);
	public List<Dept> getDeptsTreeList();
	public List<Dept> getDeptsListByParentID(String id,boolean boo);
	public List<Dept> getDeptList();
}
