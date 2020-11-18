package com.robot.service;

import com.robot.pojo.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * 收货地址管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public interface AddressService {

    /**
     * 根据用户ID，查询指定用户的收货地址
     *
     * @param uid 用户ID
     * @return 收货地址列表
     * @throws SQLException
     */
    List<Address> queryAllAddress(int uid) throws SQLException;


    /**
     * 根据地址ID，查找指定地址
     *
     * @param aid 地址ID
     * @return 指定的地址
     */
    Address queryAddressByAid(int aid) throws SQLException;

    /**
     * 删除指定用户的指定地址。
     *
     * @param uid 用户ID
     * @param aid 用户ID
     * @return 删除成功的数量
     */
    int deleteAddress(int aid, int uid) throws SQLException;

    /**
     * 为指定用户添加收货地址。
     *
     * @param address 新地址
     * @return 添加成功的数量
     */
    int addAddress(Address address) throws SQLException;

    /**
     * 设置指定地址为默认收货地址。
     *
     * @param uid 用户ID
     * @param aid 地址ID
     * @return 成功返回原来默认地址的ID，失败返回0
     */
    int setFaultAddress(int uid, int aid) throws SQLException;
}
