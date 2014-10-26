/*@文件名: PBUserService.java  @创建人: 邢健   @创建日期: 2011-11-14 下午09:53:30*/
package com.promise.cn.common.service;

import java.util.List;

import com.promise.cn.common.domain.Menu;
import com.promise.cn.common.domain.PBUser;
import com.promise.cn.framework.support.PageSupport;

/**   
 * @类名: PBUserService.java 
 * @包名: com.promise.cn.common.service 
 * @描述: 用户管理 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-11-14 下午09:53:30 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public interface PBUserService {

	/**
	 * 保存用户
	 * @param pbUser
	 * 返回1,表示成功, 0 表示用户名已经存在
	 */
	public String savePBUser(PBUser pbUser);
	/**
	 * 编辑用户
	 * @param pbUser
	 */
	public String editPBUser(PBUser pbUser);
	/**
	 * 删除用户
	 * @param id
	 */
	public void deletePBUser(String id);
	/**
	 * 获取一个用户，通过用户名和密码
	 */
	public PBUser getPBUserByUserNameAndPWD(String userName,String pwd);
	/**
	 * 检查用户名是否可以使用
	 */
	public boolean checkUserNameIsUse(String username);
	/**
	 * 检查已经存在的用户名和别人的重复
	 */
	public boolean checkOtherUserName(String username,String id);
	/**
	 * 返回所有用户对象，分页模式
	 */
	public PageSupport getUserPageSupport(List valueObject,int pageNo,int pageSize);
	/**
	 * 更新用户权限
	 */
	public String updateUserMenu(PBUser pbUser,List<Menu> listMenu);
	/**
	 * 返回所以用户，非分页模式
	 */
	public List<PBUser> getPBUserList();
}
