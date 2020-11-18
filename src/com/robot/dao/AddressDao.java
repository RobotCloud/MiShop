package com.robot.dao;

import com.robot.pojo.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * 收货地址操作。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public interface AddressDao {

    /**
     * 根据用户ID，查询指定用户的收货地址。
     *
     * @param uid 用户ID
     * @return 收货地址列表
     * @throws SQLException
     */
    List<Address> selectAllAddressByUid(int uid) throws SQLException;

    /**
     * 根据地址ID，查找指定地址。
     *
     * @param aid 地址ID
     * @return 指定的地址
     */
    Address selectAddressByAid(int aid) throws SQLException;

    /**
     * 根据用户ID，添加指定用户的地址。
     *
     * @param address 新地址
     * @return 添加成功的数量
     */
    int insertAddressByUid(Address address) throws SQLException;

    /**
     * 删除指定用户的指定收货地址。
     *
     * @param aid 收货地址ID
     * @param uid 用户ID
     * @return 删除成功的数量
     */
    int deleteAddressByUidAndId(int aid, int uid) throws SQLException;

    /**
     * 根据地址ID，修改默认状态。
     *
     * @param aid 地址ID
     * @param state 新的状态
     * @return 修改成功的数量
     */
    int changeAddressState(int aid, int state) throws SQLException;


    /**
     * 查找指定用户的默认地址ID。
     *
     * @param uid 用户ID
     * @return 默认地址的ID
     */
    int selectDefaultAddressId(int uid) throws SQLException;
}
