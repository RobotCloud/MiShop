package com.robot.dao;

import com.robot.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户操作。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public interface UserDao {

    /**
     * 查询所有用户。
     *
     * @return 所有用户
     */
    List<User> selectAllUser() throws SQLException;

    /**
     * 根据用户名和密码查询用户。
     *
     * @param username 用户名
     * @param password 密码
     * @return 找到的用户
     */
    User selectUser(String username, String password) throws SQLException;

    /**
     * 根据用户名查询用户是否存在。
     *
     * @param username 用户名
     * @return 如果存在则返回true，如果不存在则返回false
     */
    boolean selectUserByUsername(String username) throws SQLException;

    /**
     * 根据ID查询用户是否存在。
     *
     * @param id 用户ID
     * @return 如果存在，则返回true；如果不存在，则返回false
     */
    boolean selectUserById(int id) throws SQLException;

    /**
     * 添加用户。
     *
     * @param user 新用户
     * @return 如果添加成功返回添加成功的数量，如果添加失败返回0
     */
    int insertUser(User user) throws SQLException;

    /**
     * 根据ID删除用户。
     *
     * @param id 用户ID
     * @return 返回删除成功的数量
     */
    int deleteUserById(int id) throws SQLException;

    /**
     * 管理员修改用户信息。
     *
     * @param user 修改后的用户
     * @return 修改成功的数量
     */
    int updateUserOfAdministrator(User user) throws SQLException;

    /**
     * 根据激活码，查找用户的激活状态码。
     *
     * @param code 激活码
     * @return 激活状态
     */
    int selectFlagByCode(String code) throws SQLException;

    /**
     * 查找用户中有无此激活码
     *
     * @param code 激活码
     * @return 激活状态
     */
    boolean selectCode(String code) throws SQLException;

    /**
     * 激活账户，更新账户信息。
     *
     * @param code 激活码
     * @return 激活成功的数量
     */
    int updateAccount(String code) throws SQLException;
}
