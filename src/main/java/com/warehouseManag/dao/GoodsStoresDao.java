package com.warehouseManag.dao;

import com.warehouseManag.entity.GoodsStores;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * (GoodsStores)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-27 21:09:31
 */
@Mapper
public interface GoodsStoresDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsStores queryById(Integer id);

    /**
     * 通过货物和门店ID查询单条数据
     *
     * @param goodsId 条件
     * @param storeId 条件
     * @return 实例对象
     */
    GoodsStores queryByIds(@Param("goodsId") Integer goodsId,@Param("storeId") Integer storeId);

    /**
     * 查询指定行数据
     *
     * @param goodsStores 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<GoodsStores> queryAllByLimit(GoodsStores goodsStores, @Param("pageable") Pageable pageable);

    List<GoodsStores> queryGoodsByLimit(@Param("goodsId") Integer goodsId,@Param("inboundDate") Date inboundDate,
                                      @Param("page") long page,@Param("limit") long limit);
    /**
     * 统计总行数
     *
     * @param goodsStores 查询条件
     * @return 总行数
     */
    long count(GoodsStores goodsStores);

    /**
     * 统计总行数
     *
     * @param goodsId 查询条件
     * @return 总行数
     */
    long countByGoodsId(@Param("goodsId") Integer goodsId, @Param("inboundDate") Date inboundDate);

    /**
     * 检查是否存在
     *
     * @param goodsId 查询条件
     * @param storesId 查询条件
     * @return 总行数
     */
    long checkCount(@Param("goodsId") Integer goodsId,@Param("storeId") Integer storesId);

    /**
     * 新增数据
     *
     * @param goodsStores 实例对象
     * @return 影响行数
     */
    int insert(GoodsStores goodsStores);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodsStores> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GoodsStores> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodsStores> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GoodsStores> entities);

    /**
     * 修改数据
     *
     * @param goodsStores 实例对象
     * @return 影响行数
     */
    int update(GoodsStores goodsStores);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

