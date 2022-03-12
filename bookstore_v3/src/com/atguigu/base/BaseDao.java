package com.atguigu.base;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    Connection connection = null;

    public int update(String sql, Object... args) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().update(
                    connection,
                    sql,
                    args
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    public List<T> selectAll(Class<T> clazz, String sql, Object... args) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    sql,
                    new BeanListHandler<T>(clazz),
                    args
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    public T selectOne(Class<T> clazz, String sql, Object... args) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    sql,
                    new BeanHandler<T>(clazz),
                    args
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

}
