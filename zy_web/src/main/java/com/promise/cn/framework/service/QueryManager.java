/*@文件名: QueryManager.java  @创建人: 邢健   @创建日期: 2011-12-6 上午10:53:43*/
package com.promise.cn.framework.service;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import com.promise.cn.framework.exception.DaoException;
import com.promise.cn.framework.support.PageSupport;

/**   
 * @类名: QueryManager.java 
 * @包名: com.promise.cn.framework.service 
 * @描述: QueryManager.java 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-6 上午10:53:43 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public interface QueryManager {
    /**
     * 返回entity,如果没有记录，返回null
     * @param entityClass
     * @param id
     * @return
     * @throws DaoException
     */
    public Object get(Class entityClass,Serializable id) throws DaoException;
    /**
     * 返回entity,如果没有记录，抛出异常
     * @param entityClass
     * @param id
     * @return
     * @throws DaoException
     */
    public Object load(Class entityClass,Serializable id) throws DaoException;
    /**
     * 执行HQL语句,返回一个List,没有记录时，返回一个长度为0的List,尽量不要使用拼的sql语句,如果有参数，应该使用占位符
     * @param query
     * @return
     * @throws DaoException
     */
    public List find(String query) throws DaoException;
    /**
     * 执行HQL语句，value是带入的参数
     * @param query
     * @param value
     * @return
     * @throws DaoException
     */
    public List find(String query,Object value) throws DaoException;
    /**
     * 执行HQL语句，value是带入的参数数组
     * @param query
     * @param values
     * @return
     * @throws DaoException
     */
    public List find(String query,Object[] values) throws DaoException;
    /**
     * 执行HQL语句，返回分页数据
     * @param queryString
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport find(final String queryString,final int pageNo,final int pageSize) throws DaoException;
    /**
     * 执行HQL语句，返回分页数据，value是带入参数
     * @param queryString
     * @param value
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport find(final String queryString,final Object value,final int pageNo,final int pageSize) throws DaoException;
    /**
     * 执行HQL语句，返回分页数据，values是带入的参数数组
     * @param queryString
     * @param value
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport find(final String queryString,final Object[] value,final int pageNo,final int pageSize) throws DaoException;
    /**
     * 执行预定义的语句，优先使用
     * @param queryName
     * @return
     * @throws DaoException
     */
    public List findByNamedQuery(String queryName) throws DaoException;
    /**
     * 执行预定义的语句，优先使用
     * @param queryName
     * @param value
     * @return
     * @throws DaoException
     */
    public List findByNamedQuery(String queryName,Object value) throws DaoException;
    /**
     * 执行预定义的语句，优先使用
     * @param queryName
     * @param values
     * @return
     * @throws DaoException
     */
    public List findByNamedQuery(String queryName,Object[] values) throws DaoException;
    /**
     * 执行预定的语句，返回分页数据，优先使用
     * @param queryName
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport findByNamedQuery(final String queryName,final int pageNo,final int pageSize) throws DaoException;
    /**
     * 执行预定的语句，返回分页数据，value是传入的参数，优先使用
     * @param queryName
     * @param value
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport findByNamedQuery(final String queryName,final Object value,final int pageNo,final int pageSize) throws DaoException;
    /**
     * 执行预定的语句，返回分页数据，values是传入的参数优先使用
     * @param queryName
     * @param values
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport findByNamedQuery(final String queryName,final Object[] values,final int pageNo,final int pageSize) throws DaoException; 
    /**
     * 根据查询条件包装器及分页参数返回符合条件的数据列表
     * @param criteria
     * @return
     * @throws DaoException
     */
    public List find(final DetachedCriteria criteria) throws DaoException;
    /**
     * 根据查询条件包装器及分页参数返回符合条件的分页数据列表
     * @param criteria
     * @param pageNo
     * @param pageSize
     * @return
     * @throws DaoException
     */
    public PageSupport find(final DetachedCriteria criteria,final int pageNo, final int pageSize) throws DaoException;

    public PageSupport find(final DetachedCriteria cw,final Order order,final int iPageNo,final int iPageSize) throws DaoException;

    public PageSupport find(final DetachedCriteria cw,final List orders,final int iPageNo,final int iPageSize) throws DaoException;
}
