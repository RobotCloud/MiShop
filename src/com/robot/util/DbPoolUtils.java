package com.robot.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接池工具类，实现事务操作。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public class DbPoolUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 初始化连接池
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DbPoolUtils.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("连接池初始化失败", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("获取连接失败", e);
            }
        }
        return connection;
    }

    /**
     * 关闭所有连接
     *
     * @param connection 连接
     * @param statement  执行
     * @param resultSet  结果集
     */
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                statement.close();
            }
            if (connection != null) {
                // 如果没有开启事务，才可以关闭连接
                if (connection.getAutoCommit()) {
                    threadLocal.remove();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接。
     *
     * @throws SQLException
     */
    public static void close() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            threadLocal.remove();
            connection.close();
        }
    }

    /**
     * 开启事务
     *
     * @throws SQLException
     */
    public static void openTransaction() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            connection.setAutoCommit(false);
        }
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            connection.commit();
        }
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            connection.rollback();
        }
    }

}
