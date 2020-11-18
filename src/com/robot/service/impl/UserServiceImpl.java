package com.robot.service.impl;

import com.robot.dao.UserDao;
import com.robot.dao.impl.UserDaoImpl;
import com.robot.pojo.User;
import com.robot.service.UserService;
import com.robot.util.Base64Utils;
import com.robot.util.EmailUtils;
import com.robot.util.SysConstant;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

/**
 * 用户管理。
 *
 * @author 张宝旭
 * @date 2020/9/12
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    // 查询所有用户
    @Override
    public List<User> queryAllUser() throws SQLException {
        return userDao.selectAllUser();
    }

    // 根据用户名和密码查询用户
    @Override
    public User queryUser(String username, String password) throws SQLException {
        return userDao.selectUser(username, password);
    }

    // 根据用户名查询用户是否存在
    @Override
    public boolean queryUserByUsername(String username) throws SQLException {
        return userDao.selectUserByUsername(username);
    }

    // 添加用户
    @Override
    public int addUser(User user) throws SQLException, UnknownHostException {
        boolean userExist = userDao.selectUserByUsername(user.getUsername());
        if (userExist) {
            return 0;
        }
        int result = userDao.insertUser(user);
        if (result > 0) {
            // 注册成功，发送邮箱
            String title = "小米商城账户激活";
            String ip = Inet4Address.getLocalHost().getHostAddress();
            // 对激活码加密
            String baseCode = Base64Utils.encode(user.getCode());
            String url = "http://" + ip + ":8080/userServlet?method=activeAccount&baseCode=" + baseCode;
            String content = user.getUsername() + ":<br>您好,<a href='" + url + "'>请点击该链接激活账户</a>";
            EmailUtils.sendEmail(title, content, user.getEmail());
            return 1;
        }
        return 0;
    }

    // 激活账户
    @Override
    public int activeAccount(String code) throws SQLException {
        // 判断用户数据库中有无此激活码
        boolean hasCode = userDao.selectCode(code);
        if (hasCode) {
            // 根据激活码，查找激活状态标志
            int flag = userDao.selectFlagByCode(code);
            if (flag == SysConstant.NOT_ACTIVE) {
                return userDao.updateAccount(code);
            }
        } else {
            return 2;
        }
        return 0;
    }

    // 删除用户
    @Override
    public int deleteUser(int id) throws SQLException {
        boolean userExist = userDao.selectUserById(id);
        if (!userExist) {
            return 0;
        }
        return userDao.deleteUserById(id);
    }

    // 管理员修改用户信息
    @Override
    public int superUpdateUser(User user) throws SQLException {
        boolean userExist = userDao.selectUserById(user.getId());
        if (!userExist) {
            return 0;
        }
        return userDao.updateUserOfAdministrator(user);
    }
}
