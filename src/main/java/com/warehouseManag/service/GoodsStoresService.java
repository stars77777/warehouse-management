package com.warehouseManag.service;

import com.warehouseManag.entity.GoodsStores;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (GoodsStores)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 21:09:31
 */
public interface GoodsStoresService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GoodsStores queryById(Integer id);

    /**
     * 分页查询
     *
     * @param goodsStores 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<GoodsStores> queryByPage(GoodsStores goodsStores, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param goodsStores 实例对象
     * @return 实例对象
     */
    GoodsStores insert(GoodsStores goodsStores);

    /**
     * 修改数据
     *
     * @param goodsStores 实例对象
     * @return 实例对象
     */
    GoodsStores update(GoodsStores goodsStores);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 检查是否存在
     *
     * @param goodsId 查询条件
     * @param storesId 查询条件
     * @return 总行数
     */
    long checkCount(Integer goodsId,Integer storesId);

    /**
     * 通过货物和门店ID查询单条数据
     *
     * @param goodsId 条件
     * @param storeId 条件
     * @return 实例对象
     */
    GoodsStores queryByIds(Integer goodsId,Integer storeId);
}
