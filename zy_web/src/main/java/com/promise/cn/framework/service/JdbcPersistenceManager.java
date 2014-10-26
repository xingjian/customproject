/*@文件名: JdbcPersistenceManager.java  @创建人: 邢健   @创建日期: 2011-12-24 下午10:14:48*/
package com.promise.cn.framework.service;

import java.util.List;

import com.promise.cn.framework.exception.DaoException;

/**   
 * @类名: JdbcPersistenceManager.java 
 * @包名: com.promise.cn.framework.service 
 * @描述: JdbcPersistenceManager 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-24 下午10:14:48 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public interface JdbcPersistenceManager {
	//执行sql语句
    public void executeSql(String sql) throws DaoException;
    //批处理执行sql
    public void batchExecuteSql(String sql,List params) throws DaoException;
}
