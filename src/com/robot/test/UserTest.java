package com.robot.test;

import com.robot.dao.UserDao;
import com.robot.dao.impl.UserDaoImpl;
import com.robot.pojo.User;
import com.robot.service.UserService;
import com.robot.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 测试类。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public class UserTest {
    UserService userService = new UserServiceImpl();
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserTest() throws SQLException {
        User user = userService.queryUser("admin", "888");
        System.out.println(user);
    }
    @Test
    public void selectUserByIdTest() throws SQLException {
        boolean res = userDao.selectUserById(2);
        System.out.println(res);
    }

}
