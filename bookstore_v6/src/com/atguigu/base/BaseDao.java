package com.atguigu.base;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public Long selectCount(String sql, Object... args) {
        try {
            connection = JDBCUtils.getConnection();
            return new QueryRunner().query(
                    connection,
                    sql,
                    new ScalarHandler<>(),
                    args
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.release(connection, null);
        }
    }

    /**
     * 添加记录并获取自增主键值
     * @param sql
     * @param args
     * @return
     */
    public Integer insertGeneratedKey(String sql, Object... args) {
        Integer generatedKey = null;
        try {
            connection = JDBCUtils.getConnection();
            generatedKey = new QueryRunner().insert(
                    connection,
                    sql,
                    new ResultSetHandler<Integer>() {
                        @Override
                        public Integer handle(ResultSet resultSet) throws SQLException {
                            while (resultSet.next())
                                return resultSet.getInt(1);
                            return null;
                        }
                    },
                    args
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, null);
        }
        return generatedKey;
    }

    public int[] batch(String sql,Object[][] args){
        try {
            connection = JDBCUtils.getConnection();
            int[] batch = new QueryRunner().batch(
                    connection,
                    sql,
                    args
            );
            return batch;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection,null);
        }
        return null;
    }

}
