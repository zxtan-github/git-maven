package org.ifunq.tanzx.spring.JDBC.mybatis.dao;

import org.ifunq.tanzx.spring.JDBC.mybatis.DO.SkuBarcodeDO;
import org.springframework.dao.DataAccessException;

/**
 * 基本商品条码Dao
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-07-12 14:50
 **/
public interface SkuBarcodeDao {

    /**
     * 添加实体
     *
     * @param entity 要添加的实体对象
     * @return
     */
    public void insert(SkuBarcodeDO entity) throws DataAccessException;


    /**
     * 新建对象或更新对象
     *
     * @param entity 要保存的实体对象
     * @return
     */
    public void insertOrUpdate(SkuBarcodeDO entity) throws DataAccessException;


    /**
     * 保存实体
     *
     * @param entity 要保存的实体对象
     * @return
     */
    public int update(SkuBarcodeDO entity) throws DataAccessException;

    /**
     * 根据主键删除实体
     *
     * @param pk 主键
     * @return
     */
    public int deleteById(Long pk) throws DataAccessException;


    /**
     * 根据主键返回指定的实体对象
     *
     * @param pk 主键
     * @return
     */
    public SkuBarcodeDO getById(Long pk) throws DataAccessException;

    /**
     * 根据主键返回指定的实体对象, 会锁表
     *
     * @param pk
     * @return
     * @throws DataAccessException
     */
    public SkuBarcodeDO getByIdForUpdate(Long pk) throws DataAccessException;
}
