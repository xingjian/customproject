/*@文件名: DaoException.java  @创建人: 邢健   @创建日期: 2011-12-6 上午10:28:46*/
package com.promise.cn.framework.exception;

/**   
 * @类名: DaoException.java 
 * @包名: com.promise.cn.framework.exception 
 * @描述: 运行时数据库异常 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-6 上午10:28:46 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public class DaoException extends RuntimeException {
	   
    public DaoException(Throwable ex) {
        super(ex);
    }
     
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String msg, Throwable ex) {
        super(msg, ex);
    }
 
}