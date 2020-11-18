package com.robot.dao.impl;

import com.robot.dao.AddressDao;
import com.robot.pojo.Address;
import com.robot.util.DbPoolUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 地址操作。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class AddressDaoImpl implements AddressDao {
    QueryRunner queryRunner = new QueryRunner();

    // 根据用户ID，查询指定用户的收货地址
    @Override
    public List<Address> selectAllAddressByUid(int uid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT a_id as aid, u_id as uid, a_name as name, a_phone as phone, a_detail as detail, a_state as state" +
                    " FROM address WHERE u_id=?";
            return queryRunner.query(connection, sql, new BeanListHandler<>(Address.class), uid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据地址ID，查询指定地址
    @Override
    public Address selectAddressByAid(int aid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT a_id as aid, u_id as uid, a_name as name, a_phone as phone, a_detail as detail, a_state as state" +
                    " FROM address WHERE a_id=?";
            return queryRunner.query(connection, sql, new BeanHandler<>(Address.class), aid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 为指定用户添加收货地址
    @Override
    public int insertAddressByUid(Address address) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "INSERT INTO address (a_id, u_id, a_name, a_phone, a_detail, a_state) VALUES(?,?,?,?,?,?)";
            Object[] params = {address.getAid(), address.getUid(), address.getName(), address.getPhone(), address.getDetail(), address.getState()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 删除指定用户的指定地址
    @Override
    public int deleteAddressByUidAndId(int aid, int uid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "DELETE FROM address WHERE a_id=? AND u_id=?";
            Object[] params = {aid, uid};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据地址ID，修改默认状态
    @Override
    public int changeAddressState(int aid, int state) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "UPDATE address SET a_state=? WHERE a_id=?";
            return queryRunner.update(connection, sql, state, aid);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 查询指定用户的默认地址
    @Override
    public int selectDefaultAddressId(int uid) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT a_id as id FROM address WHERE u_id=? AND a_state=1";
            List<Integer> state = queryRunner.query(connection, sql, new ColumnListHandler<>(), uid);
            return state.get(0);
        } finally {
            DbPoolUtils.close();
        }
    }

}
