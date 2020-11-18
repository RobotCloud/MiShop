package com.robot.service.impl;

import com.robot.dao.AddressDao;
import com.robot.dao.impl.AddressDaoImpl;
import com.robot.pojo.Address;
import com.robot.service.AddressService;
import com.robot.util.DbPoolUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 地址管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();

    // 查询指定用户的所有地址
    @Override
    public List<Address> queryAllAddress(int uid) throws SQLException {
        return addressDao.selectAllAddressByUid(uid);
    }

    // 根据地址ID查询指定地址
    @Override
    public Address queryAddressByAid(int aid) throws SQLException {
        return addressDao.selectAddressByAid(aid);
    }

    // 删除指定用户的地址
    @Override
    public int deleteAddress(int aid, int uid) throws SQLException {
        Address address = addressDao.selectAddressByAid(aid);
        if (address != null) {
            return addressDao.deleteAddressByUidAndId(aid, uid);
        } else {
            return -1;
        }
    }

    // 为指定用户添加收货地址
    @Override
    public int addAddress(Address address) throws SQLException {
        return addressDao.insertAddressByUid(address);
    }

    // 设置指定地址为默认地址
    @Override
    public int setFaultAddress(int uid, int aid) throws SQLException {
        try {
            // 开启事务
            DbPoolUtils.openTransaction();
            // 1、找到默认地址的ID
            int defaultAddressId = addressDao.selectDefaultAddressId(uid);
            // 2、清除默认地址
            int changeResult = addressDao.changeAddressState(defaultAddressId, 0);
            // 3、设置新的默认地址
            addressDao.changeAddressState(aid, 1);
            DbPoolUtils.commit();
            return defaultAddressId;
        } catch (SQLException e) {
            e.printStackTrace();
            DbPoolUtils.rollback();
            return 0;
        }
    }
}
