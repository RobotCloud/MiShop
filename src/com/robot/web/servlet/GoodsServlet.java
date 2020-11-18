package com.robot.web.servlet;

import com.robot.pojo.Goods;
import com.robot.pojo.Types;
import com.robot.service.GoodsService;
import com.robot.service.TypesService;
import com.robot.service.impl.GoodsServiceImpl;
import com.robot.service.impl.TypesServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 商品处理。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
@WebServlet(name = "GoodsServlet", value = "/goodsServlet")
public class GoodsServlet extends BaseServlet {
    TypesService typesService = new TypesServiceImpl();
    GoodsService goodsService = new GoodsServiceImpl();

    // 查询所有商品类别
    public List<Types> queryAllTypes(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return typesService.queryAllTypes();
    }

    // 查询指定类型的所有商品
    public List<Goods> queryAllGoodsByTypeId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        return goodsService.queryGoodsByTypeId(typeId, 0, 5);
    }

    // 根据商品ID，查询指定商品
    public Goods queryGoodsDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        return goodsService.queryGoodsById(goodsId);
    }
}
