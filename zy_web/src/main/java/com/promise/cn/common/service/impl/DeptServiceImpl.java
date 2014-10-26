/** @文件名: DeptServiceImpl.java @创建人：邢健  @创建日期： 2013-10-14 上午10:46:03 */
package com.promise.cn.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import com.promise.cn.common.domain.Dept;
import com.promise.cn.common.service.DeptService;
import com.promise.cn.framework.service.JdbcPersistenceManager;
import com.promise.cn.framework.service.PersistenceManager;
import com.promise.cn.framework.service.QueryManager;

/**   
 * @类名: DeptServiceImpl.java 
 * @包名: com.promise.cn.common.service.impl 
 * @描述: TODO 
 * @作者: xingjian xingjian@yeah.net   
 * @日期:2013-10-14 上午10:46:03 
 * @版本: V1.0   
 */
@SuppressWarnings("all")
@Service("deptService")
@RemotingDestination(channels={"my-amf","my-secure-amf"})
public class DeptServiceImpl implements DeptService {

	//日志对象
	private Logger log = LoggerFactory.getLogger(DeptServiceImpl.class);
	//pm对象
	private PersistenceManager persistenceManager;
	//查询对象
	private QueryManager queryManager;
	//执行sql使用
	private JdbcPersistenceManager jdbcPersistenceManager;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	@RemotingInclude
	public String saveDept(Dept parent,Dept dept) {
		dept.setParent(parent);
		if(parent.getLeaf().equals("1")){
			parent.setLeaf("0");
			persistenceManager.update(parent);
		}
		dept.setCreateTime(sdf.format(new Date()));
		persistenceManager.save(dept);
		return "success";
	}

	@Override
	@RemotingInclude
	public String editDept(Dept dept) {
		persistenceManager.update(dept);
		return "success";
	}

	@Override
	@RemotingInclude
	public String deleteDept(String id) {
		persistenceManager.remove(Dept.class, id);
		return "success";
	}

	@Override
	@RemotingInclude
	public List<Dept> getDeptsTreeList() {
		String hql = "from Dept t where t.id='root'";
		List<Dept> retList = queryManager.find(hql);
		for(Iterator<Dept> items = retList.iterator();items.hasNext();){
			Dept deptTemp = items.next();
			if(deptTemp.getLeaf().equals("0")){
				deptTemp.children = getDeptsListByParentID(deptTemp.getId(),true);
			}
		}
		log.debug("部门集合大小为:"+retList.size()+"");
		return retList;
	}

	@Override
	@RemotingInclude
	public List<Dept> getDeptsListByParentID(String id, boolean boo) {
		String hql = "from Dept t where t.parent.id='"+id+"'";
		List<Dept> retList = queryManager.find(hql);
		if(boo){
			for(Iterator<Dept> items = retList.iterator();items.hasNext();){
				Dept deptTemp = items.next();
				if(deptTemp.getLeaf().equals("0")){
					deptTemp.children = getDeptsListByParentID(deptTemp.getId(),true);
				}
			}
		}
		return retList;
	}

	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	public void setQueryManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}

	public void setJdbcPersistenceManager(
			JdbcPersistenceManager jdbcPersistenceManager) {
		this.jdbcPersistenceManager = jdbcPersistenceManager;
	}

	@Override
	@RemotingInclude
	public List<Dept> getDeptList() {
		String hql = "from Dept t where t.id!='root'";
		List<Dept> list = queryManager.find(hql);
		return list;
	}

}
