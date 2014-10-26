/*@文件名: MenuService.java  @创建人: 邢健   @创建日期: 2011-12-14 上午10:47:36*/
package com.promise.cn.common.service;

import java.util.List;

import com.promise.cn.common.domain.Menu;

/**   
 * @类名: MenuService.java 
 * @包名: com.promise.cn.common.service 
 * @描述: 菜单接口 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-14 上午10:47:36 
 * @版本 V1.0   
 */
public interface MenuService {

	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	public String saveMenu(Menu parent,Menu menu);
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	public String updateMenu(Menu menu);
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	public String deleteMenu(String id);
	/**
	 * 获取菜单树形结构
	 * @return
	 */
	public List<Menu> getMenusTreeList();
	/**
	 * 通过父节点的ID,返回集合
	 */
	public List<Menu> getMenusListByParentID(String id,boolean boo);
}
