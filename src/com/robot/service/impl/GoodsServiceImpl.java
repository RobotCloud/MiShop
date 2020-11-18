package com.robot.service.impl;

import com.robot.dao.GoodsDao;
import com.robot.dao.impl.GoodsDaoImpl;
import com.robot.pojo.Goods;
import com.robot.service.GoodsService;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品管理。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();

    // 分页查询指定类型的所有商品
    @Override
    public List<Goods> queryGoodsByTypeId(int typeId, int startPage, int endPage) throws SQLException {
        return goodsDao.selectGoodsByTypeId(typeId, startPage, endPage);
    }

    // 根据商品ID，查询指定商品
    @Override
    public Goods queryGoodsById(int goodsId) throws SQLException {
        return goodsDao.selectGoodsById(goodsId);
    }
}
