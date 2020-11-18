package com.robot.dao.impl;

import com.robot.dao.UserDao;
import com.robot.pojo.User;
import com.robot.util.DbPoolUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 操作用户。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public class UserDaoImpl implements UserDao {
    // 创建执行对象
    QueryRunner queryRunner = new QueryRunner();

    // 查询所有用户
    @Override
    public List<User> selectAllUser() throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT id, username, password, email, name, age, gender, code, flag, role FROM user";
            return queryRunner.query(connection, sql, new BeanListHandler<>(User.class));
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据用户名和密码查询用户
    @Override
    public User selectUser(String username, String password) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT id, username, password, email, name, age, gender, code, flag, role FROM user WHERE username=? AND password=?";
            Object[] params = {username, password};
            return queryRunner.query(connection, sql, new BeanHandler<>(User.class), params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据用户名查询用户是否存在
    @Override
    public boolean selectUserByUsername(String username) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT id FROM user WHERE username=?";
            User user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), username);
            return user != null;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据ID查询用户是否存在
    @Override
    public boolean selectUserById(int id) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT id FROM user WHERE id=?";
            User user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), id);
            return user != null;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 添加用户
    @Override
    public int insertUser(User user) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "INSERT INTO user(id, username, password, email, name, age, gender, code, flag, role) values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(),
                    user.getAge(), user.getGender(), user.getCode(), user.getFlag(), user.getRole()};
            int update = queryRunner.update(connection, sql, params);
            return update;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据ID删除用户
    @Override
    public int deleteUserById(int id) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "DELETE FROM user WHERE id=?";
            return queryRunner.update(connection, sql, id);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 管理员修改用户信息
    @Override
    public int updateUserOfAdministrator(User user) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "UPDATE user SET username=?, password=?, email=?, name=?, age=?, gender=?, role=? WHERE id=?";
            Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getAge(),
                    user.getGender(), user.getRole(), user.getId()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据激活码查找状态码
    @Override
    public int selectFlagByCode(String code) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT flag FROM user WHERE code=?";
            return queryRunner.query(connection, sql, new ScalarHandler<>(), code);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 查找有无此激活码
    @Override
    public boolean selectCode(String code) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT code FROM user WHERE code=?";
            Object queryCode = queryRunner.query(connection, sql, new ScalarHandler<>(), code);
            return queryCode != null;
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据激活码激活账户
    @Override
    public int updateAccount(String code) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "UPDATE user SET flag=1, code=null WHERE code=?";
            return queryRunner.update(connection, sql, code);
        } finally {
            DbPoolUtils.close();
        }
    }

}
