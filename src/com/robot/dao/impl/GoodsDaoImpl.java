package com.robot.dao.impl;

import com.robot.dao.GoodsDao;
import com.robot.pojo.Goods;
import com.robot.util.DbPoolUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 商品操作。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class GoodsDaoImpl implements GoodsDao {
    QueryRunner queryRunner = new QueryRunner();

    // 根据商品类型ID，查询指定类型商品的数量
    @Override
    public Long selectGoodsCountByTypeID(int typeId) throws SQLException {
        Connection connection;
        try {
            connection = DbPoolUtils.getConnection();
            String sql = "SELECT COUNT(id) FROM goods WHERE t_id=?";
            return (Long) queryRunner.query(connection, sql, new ScalarHandler(), typeId);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 查询某一类型的所有商品
    @Override
    public List<Goods> selectGoodsByTypeId(int typeId, int startPage, int endPage) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT g_id as id, t_id as typesId, g_name as name, g_time as time, g_image as image," +
                    " g_price as price, g_state as state, g_info as info FROM goods WHERE t_id=? LIMIT ?,?";
            Object[] params = {typeId, startPage, endPage};
            return queryRunner.query(connection, sql, new BeanListHandler<>(Goods.class), params);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 根据商品ID查询商品
    @Override
    public Goods selectGoodsById(int id) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT g_id as id, t_id as typesId, g_name as name, g_time as time, g_image as image, g_price as price," +
                    " g_state as state, g_info as info FROM goods WHERE g_id=?";
            return queryRunner.query(connection, sql, new BeanHandler<>(Goods.class), id);
        } finally {
            DbPoolUtils.close();
        }
    }

    // 添加一条商品
    @Override
    public int insertGoods(Goods goods) throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "INSERT INTO goods (g_id, t_id, g_name, g_time, g_image, g_price, g_state, g_info) VALUES(?,?,?,?,?,?,?,?)";
            Object[] params = {goods.getId(), goods.getTypesId(), goods.getName(), goods.getTime(), goods.getImage(),
                    goods.getPrice(), goods.getState(), goods.getInfo()};
            return queryRunner.update(connection, sql, params);
        } finally {
            DbPoolUtils.close();
        }
    }
}
