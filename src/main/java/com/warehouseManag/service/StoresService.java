package com.warehouseManag.service;

import com.warehouseManag.entity.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (Stores)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 15:08:06
 */
public interface StoresService {

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    Stores queryById(Integer storeId);

    /**
     * 分页查询
     *
     * @param storesName 筛选条件
     * @param page 当前页
     * @param limit 记录数
     * @return 查询结果
     */
    Map<String, Object> queryByPage(String storesName, long page, long limit);

    /**
     * 新增数据
     *
     * @param stores 实例对象
     * @return 实例对象
     */
    Stores insert(Stores stores);

    /**
     * 修改数据
     *
     * @param stores 实例对象
     * @return 实例对象
     */
    Stores update(Stores stores);

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer storeId);

}
