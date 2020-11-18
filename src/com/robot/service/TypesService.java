package com.robot.service;

import com.robot.pojo.Types;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品类型管理。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public interface TypesService {

    /**
     * 查询所有商品类别。
     *
     * @return 所有商品类别的列表
     * @throws SQLException
     */
    List<Types> queryAllTypes() throws SQLException;
}
