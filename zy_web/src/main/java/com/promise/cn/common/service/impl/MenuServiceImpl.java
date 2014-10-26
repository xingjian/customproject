/*@文件名: MenuServiceImpl.java  @创建人: 邢健   @创建日期: 2011-12-14 下午02:18:01*/
package com.promise.cn.common.service.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import com.promise.cn.common.domain.Menu;
import com.promise.cn.common.service.MenuService;
import com.promise.cn.framework.service.JdbcPersistenceManager;
import com.promise.cn.framework.service.PersistenceManager;
import com.promise.cn.framework.service.QueryManager;

/**   
 * @类名: MenuServiceImpl.java 
 * @包名: com.promise.cn.common.service.impl 
 * @描述: 菜单服务接口实现类 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-14 下午02:18:01 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
@Service("menuService")
@RemotingDestination(channels={"my-amf","my-secure-amf"})
public class MenuServiceImpl implements MenuService {

	//日志对象
	private Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
	//pm对象
	private PersistenceManager persistenceManager;
	//查询对象
	private QueryManager queryManager;
	//执行sql使用
	private JdbcPersistenceManager jdbcPersistenceManager;
	
	/**
	 * 保存菜单
	 */
	@RemotingInclude
	@Override
	public String saveMenu(Menu parent,Menu menu) {
		menu.setParent(parent);
		if(parent.getLeaf().equals("1")){
			parent.setLeaf("0");
			persistenceManager.update(parent);
		}
		persistenceManager.save(menu);
		return "success";
	}

	/**
	 * 修改菜单
	 */
	@RemotingInclude
	@Override
	public String updateMenu(Menu menu) {
		persistenceManager.update(menu);
		return "success";
	}

	/**
	 * 删除菜单
	 */
	@RemotingInclude
	@Override
	public String deleteMenu(String id) {
		String sql = "delete from pbuser_menu where menuid='"+id+"'";
		jdbcPersistenceManager.executeSql(sql);
		//删除PBUser和Menu的中间表
		persistenceManager.remove(Menu.class, id);
		return "success";
	}

	/**
	 * 获取树形菜单集合
	 */
	@Override
	@RemotingInclude
	public List<Menu> getMenusTreeList(){
		String hql = "from Menu t where t.id='root'";
		List<Menu> retList = queryManager.find(hql);
		for(Iterator<Menu> items = retList.iterator();items.hasNext();){
			Menu menuTemp = items.next();
			if(menuTemp.getLeaf().equals("0")){
				menuTemp.children = getMenusListByParentID(menuTemp.getId(),true);
			}
		}
		return retList;
	}

	/**
	 * 通过父节点ID,返回集合
	 */
	@Override
	@RemotingInclude
	public List<Menu> getMenusListByParentID(String id,boolean boo) {
		String hql = "from Menu t where t.parent.id='"+id+"'";
		List<Menu> retList = queryManager.find(hql);
		if(boo){
			for(Iterator<Menu> items = retList.iterator();items.hasNext();){
				Menu menuTemp = items.next();
				if(menuTemp.getLeaf().equals("0")){
					menuTemp.children = getMenusListByParentID(menuTemp.getId(),true);
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
}
