package com.robot.service;

import com.robot.pojo.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public interface GoodsService {
    /**
     * 分页查询某类商品.
     *
     * @param typeId    商品类型ID
     * @param startPage 起始页
     * @param endPage   结束页
     * @return 商品列表
     */
    List<Goods> queryGoodsByTypeId(int typeId, int startPage, int endPage) throws SQLException;

    /**
     * 根据商品ID，查询指定商品。
     *
     * @param goodsId 商品ID
     * @return 指定商品
     */
    Goods queryGoodsById(int goodsId) throws SQLException;
}
