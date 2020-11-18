package com.robot.dao.impl;

import com.robot.dao.TypesDao;
import com.robot.pojo.Types;
import com.robot.util.DbPoolUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 商品类别操作。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class TypesDaoImpl implements TypesDao {
    QueryRunner queryRunner = new QueryRunner();

    // 查询所有商品类型
    @Override
    public List<Types> selectAllTypes() throws SQLException {
        try {
            Connection connection = DbPoolUtils.getConnection();
            String sql = "SELECT t_id as id, t_name as name, t_info as info FROM types";
            return queryRunner.query(connection,sql, new BeanListHandler<>(Types.class));
        } finally {
            DbPoolUtils.close();
        }
    }
}
