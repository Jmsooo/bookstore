package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        JDBCUtils.getConnection().setAutoCommit(false);
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        JDBCUtils.getConnection().commit();
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        JDBCUtils.getConnection().rollback();
    }

    /**
     * 释放资源
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        if (connection != null){
            threadLocal.remove();
            connection.close();
        }
    }

    static {
        Properties properties = new Properties();

        try {
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void release(Connection connection, Statement statement) {
        release(connection, statement, null);
    }

}
