package org.ifunq.tanzx.spring.JDBC.mybatis.dao;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

/**
 * 基本商品条码Dao
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-07-12 14:50
 **/
public class SkuBarcodeImplDao extends SqlSessionDaoSupport {

    /**
     * 添加实体
     *
     * @param entity 要添加的实体对象
     * @return
     */
    public void insert(SkuBarcodeDO entity) throws DataAccessException {
        getSqlSession().insert("org.ifunq.tanzx.spring.JDBC.mybatis.dao.SkuBarcodeDao.insert", entity);
    }

}
