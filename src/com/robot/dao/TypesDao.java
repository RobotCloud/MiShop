package com.robot.dao;

import com.robot.pojo.Types;

import java.sql.SQLException;
import java.util.List;

/**
 * 操作商品类别。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public interface TypesDao {

    /**
     * 查询所有商品类别。
     *
     * @return 所有商品类别的列表
     * @throws SQLException
     */
    List<Types> selectAllTypes()throws SQLException;
}
