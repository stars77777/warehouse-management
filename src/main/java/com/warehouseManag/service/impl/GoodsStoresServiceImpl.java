package com.warehouseManag.service.impl;

import com.warehouseManag.entity.GoodsStores;
import com.warehouseManag.dao.GoodsStoresDao;
import com.warehouseManag.service.GoodsStoresService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (GoodsStores)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 21:09:31
 */
@Service
public class GoodsStoresServiceImpl implements GoodsStoresService {

    @Resource
    private GoodsStoresDao goodsStoresDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GoodsStores queryById(Integer id) {
        return this.goodsStoresDao.queryById(id);
    }

    @Override
    public long checkCount(Integer goodsId, Integer storesId) {
        return this.goodsStoresDao.checkCount(goodsId,storesId);
    }

    @Override
    public GoodsStores queryByIds(Integer goodsId, Integer storeId) {
        return goodsStoresDao.queryByIds(goodsId,storeId);
    }
    /**
     * 分页查询
     *
     * @param goodsStores 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<GoodsStores> queryByPage(GoodsStores goodsStores, PageRequest pageRequest) {
        long total = this.goodsStoresDao.count(goodsStores);
        return new PageImpl<>(this.goodsStoresDao.queryAllByLimit(goodsStores, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param goodsStores 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsStores insert(GoodsStores goodsStores) {
        this.goodsStoresDao.insert(goodsStores);
        return goodsStores;
    }

    /**
     * 修改数据
     *
     * @param goodsStores 实例对象
     * @return 实例对象
     */
    @Override
    public GoodsStores update(GoodsStores goodsStores) {
        this.goodsStoresDao.update(goodsStores);
        return this.queryById(goodsStores.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.goodsStoresDao.deleteById(id) > 0;
    }
}
