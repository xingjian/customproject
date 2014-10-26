/*@文件名: PBUserServiceImpl.java  @创建人: 邢健   @创建日期: 2011-12-6 下午02:46:02*/
package com.promise.cn.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import com.promise.cn.common.domain.Menu;
import com.promise.cn.common.domain.PBUser;
import com.promise.cn.common.service.PBUserService;
import com.promise.cn.framework.service.PersistenceManager;
import com.promise.cn.framework.service.QueryManager;
import com.promise.cn.framework.support.PageSupport;

/**   
 * @类名: PBUserServiceImpl.java 
 * @包名: com.promise.cn.common.service.Impl 
 * @描述: 用户管理 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-6 下午02:46:02 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
@Service("pbUserService")
@RemotingDestination(channels={"my-amf","my-secure-amf"})
public class PBUserServiceImpl implements PBUserService {
	//日志对象
	private Logger log = LoggerFactory.getLogger(PBUserServiceImpl.class);
	//pm对象
	private PersistenceManager persistenceManager;
	//查询对象
	private QueryManager queryManager;
	
	/**
	 * 增加用户
	 * 返回1,表示成功, 0 表示用户名已经存在
	 */
	@Override
	@RemotingInclude
	public String savePBUser(PBUser pbUser) {
		if(checkUserNameIsUse(pbUser.getName())){
			persistenceManager.save(pbUser);
			log.debug("Save PBUser: "+pbUser.getName());
			return "1";
		}else{
			return "0";
		}
	}

	/**
	 * 编辑用户
	 */
	@Override
	@RemotingInclude
	public String editPBUser(PBUser pbUser) {
		if(checkOtherUserName(pbUser.getName(),pbUser.getId())){
			persistenceManager.update(pbUser);
			log.debug("Edit PBUser: "+pbUser.getName());
			return "1";
		}else{
			return "0";
		}
	}

	/**
	 * 删除用户
	 */
	@Override
	@RemotingInclude
	public void deletePBUser(String id) {
		persistenceManager.remove(PBUser.class, id);
	}
	
	/**
	 * 获取用户
	 */
	@Override
	@RemotingInclude
	public PBUser getPBUserByUserNameAndPWD(String userName, String pwd) {
		String hql = "from PBUser p where p.name='"+userName+"' and p.password='"+pwd+"'";
		List<PBUser> list = queryManager.find(hql);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 检验用户名称是否可以使用
	 */
	@Override
	@RemotingInclude
	public boolean checkUserNameIsUse(String username) {
		String hql = "from PBUser p where p.name='"+username+"'";
		List<PBUser> list = queryManager.find(hql);
		if(null!=list&&list.size()>0){
			return false;
		}
		return true;
	}
	/**
	 * 检验用户名称是否可以使用(编辑用户时候调用该方法)
	 */
	@Override
	@RemotingInclude
	public boolean checkOtherUserName(String username,String id){
		String hql = "from PBUser p where p.name='"+username+"' and p.id!='"+id+"'";
		List<PBUser> list = queryManager.find(hql);
		if(null!=list&&list.size()>0){
			return false;
		}
		return true;
	}
	
	//分页模式返回用户信息
	@Override
	@RemotingInclude
	public PageSupport getUserPageSupport(List valueObject,int pageNo,int pageSize){
		String sql = "from PBUser p";
		if(null==valueObject||valueObject.size()==0){//查询全部
			
		}else{//带条件查询
			
		}
		return queryManager.find(sql, pageNo, pageSize);
	}
	
	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}


	public void setQueryManager(QueryManager queryManager) {
		this.queryManager = queryManager;
	}

	/**
	 * 更新或者添加用户权限
	 */
	@Override
	@RemotingInclude
	public String updateUserMenu(PBUser pbUser, List<Menu> listMenu) {
		if(pbUser.getMenus()!=null&&pbUser.getMenus().size()>0){
			BeanUtils.copyProperties(listMenu, pbUser.getMenus());
			persistenceManager.update(pbUser);
			return "1";
		}else{
			pbUser.setMenus(listMenu);
			persistenceManager.update(pbUser);
			return "1";
		}
	}

	@Override
	@RemotingInclude
	public List<PBUser> getPBUserList() {
		String hql = "from PBUser";
		return queryManager.find(hql);
	}
}
