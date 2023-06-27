package com.warehouseManag.service;

import com.warehouseManag.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (Goods)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 15:06:14
 */
public interface GoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    Goods queryById(Integer goodsId);

    /**
     * 分页查询
     *
     * @param goodsName 筛选条件
     * @param page 当前页
     * @param limit 记录数
     * @return 查询结果
     */
    Map<String, Object> queryByPage(String goodsName, long page, long limit);

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    Goods insert(Goods goods);

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    Goods update(Goods goods);

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer goodsId);

}
