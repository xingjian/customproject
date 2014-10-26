/*@文件名: JdbcPersistenceManagerImpl.java  @创建人: 邢健   @创建日期: 2011-12-24 下午10:17:54*/
package com.promise.cn.framework.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.promise.cn.framework.exception.DaoException;
import com.promise.cn.framework.service.JdbcPersistenceManager;

/**   
 * @类名: JdbcPersistenceManagerImpl.java 
 * @包名: com.promise.cn.framework.service.impl 
 * @描述: JdbcPersistenceManagerImpl 
 * @作者: 邢健 xingjian@dhcc.com.cn   
 * @日期: 2011-12-24 下午10:17:54 
 * @版本 V1.0   
 */
@SuppressWarnings("all")
public class JdbcPersistenceManagerImpl implements JdbcPersistenceManager{

	protected final Logger log = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            return con;
        }catch (SQLException e) {
                throw e;
        }
    }

    /**
     * 执行sql
     */
    @Override
    public void executeSql(String sql) throws DaoException{
            Connection cn = null;
            Statement stmt = null;
            try {
                cn = getConnection();
                stmt = cn.createStatement();
                stmt.execute(sql);
            } catch (SQLException e) {
                 throw new DaoException(e);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (cn != null) {
                        cn.close();
                    }
                } catch (Exception ex) {
                    log.error(ex.getMessage());
                }
            }
    }

    /**
     * 批处理执行sql
     */
	@Override
	public void batchExecuteSql(String sql, List params) throws DaoException {
		
	}

}
