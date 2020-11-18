package com.robot.service;

import com.robot.pojo.User;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户管理。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public interface UserService {

    /**
     * 查询所有用户。
     *
     * @return 所有用户
     */
    List<User> queryAllUser() throws SQLException;

    /**
     * 根据用户名和密码查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 找到的用户
     */
    User queryUser(String username, String password) throws SQLException;

    /**
     * 根据用户名查询用户是否存在。
     *
     * @param username 用户名
     * @return 如果存在则返回true，如果不存在则返回false
     */
    boolean queryUserByUsername(String username) throws SQLException;

    /**
     * 添加用户。
     *
     * @param user 新用户
     * @return 如果添加成功返回添加成功的数量，如果添加失败返回0
     */
    int addUser(User user) throws SQLException, UnknownHostException;

    /**
     * 根据ID删除用户。
     *
     * @param id 用户ID
     * @return 返回删除成功的数量
     */
    int deleteUser(int id) throws SQLException;

    /**
     * 管理员修改用户信息。
     *
     * @param user 修改后的用户
     * @return 修改成功的数量
     */
    int superUpdateUser(User user) throws SQLException;

    /**
     * 激活账户。
     *
     * @param code 激活码
     * @return 激活成功的数量
     */
    int activeAccount(String code) throws SQLException;
}
