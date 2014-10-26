/*@文件名: PersistenceManagerImpl.java  @创建人: 邢健   @创建日期: 2011-12-6 上午10:31:28*/
package com.promise.cn.framework.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.promise.cn.framework.exception.DaoException;
import com.promise.cn.framework.service.PersistenceManager;

/**   
 * @类名: PersistenceManagerImpl.java 
 * @包名: com.promise.cn.framework.service.impl 
 * @描述: PersistenceManager接口实现类 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-6 上午10:31:28 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public class HibernatePersistenceManagerImpl extends HibernateDaoSupport implements PersistenceManager {

	/**
	 * 保存实体对象
	 */
	@Override
	public void save(Object entity) throws DaoException {
		this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * 保存或者更新实体对象
	 */
	@Override
	public void saveOrUpdate(Object entity) throws DaoException {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 保存实体对象，是否立刻刷新
	 */
	@Override
	public void save(final Object entity, final boolean flush) throws DaoException {
		try{
            HibernateCallback callback = new HibernateCallback(){
                    public Object doInHibernate(Session session) throws HibernateException,SQLException{
                            session.save(entity);                                
                            if(flush){
                                    session.flush();
                                    session.clear();
                            }
                            return null;
                    }
            };
            this.getHibernateTemplate().execute(callback);
	    }catch(Exception e){
	            throw new DaoException(e);
	    }
	}
	
	/**
	 * 更新实体对象
	 */
	@Override
	public void update(Object entity) throws DaoException {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * 删除实体对象
	 */
	@Override
	public void remove(Object entity) throws DaoException {
		 this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 通过实体ID删除对象
	 */
	@Override
	public void remove(Class clz, Serializable id) throws DaoException {
		 Object entity = this.getHibernateTemplate().load(clz,id);
         this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 批量删除实体对象
	 */
	@Override
	public void removeAll(Collection entities) throws DaoException {
		for(Object entity:entities){
            this.getHibernateTemplate().delete(entity);
		}
	}

	/**
	 * 通过ID集合批量删除实体对象
	 */
	@Override
	public void removeAll(Class clz, Serializable[] ids) throws DaoException {
		for(Serializable id : ids){
            remove(clz,id);
		}
	}

}
