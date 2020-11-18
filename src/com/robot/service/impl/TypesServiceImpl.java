package com.robot.service.impl;

import com.robot.dao.TypesDao;
import com.robot.dao.impl.TypesDaoImpl;
import com.robot.pojo.Types;
import com.robot.service.TypesService;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品类型管理。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class TypesServiceImpl implements TypesService {
    TypesDao typesDao = new TypesDaoImpl();

    // 查询所有商品类别
    @Override
    public List<Types> queryAllTypes() throws SQLException {
        return typesDao.selectAllTypes();
    }
}
