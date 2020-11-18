package com.robot.dao;

import com.robot.pojo.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品操作。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public interface GoodsDao {

    /**
     * 查询某类商品的数量。
     *
     * @param typeId 商品类别ID
     * @return 商品数量
     */
    Long selectGoodsCountByTypeID(int typeId) throws SQLException;

    /**
     * 分页查询某类商品.
     *
     * @param typeId 商品类型ID
     * @param startPage 起始页
     * @param endPage 结束页
     * @return 商品列表
     */
    List<Goods> selectGoodsByTypeId(int typeId, int startPage, int endPage) throws SQLException;

    /**
     * 根据ID查询商品
     * @param id 商品ID
     * @return 商品
     */
    Goods selectGoodsById(int id) throws SQLException;

    /**
     * 添加商品。
     *
     * @param goods 新商品
     * @return 添加成功的数量
     */
    int insertGoods(Goods goods) throws SQLException;

}
