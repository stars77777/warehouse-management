package com.warehouseManag.service.impl;

import com.warehouseManag.entity.Stores;
import com.warehouseManag.dao.StoresDao;
import com.warehouseManag.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (Stores)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 15:08:06
 */
@Service
public class StoresServiceImpl implements StoresService {
    @Resource
    StoresDao storesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    @Override
    public Stores queryById(Integer storeId) {
        return this.storesDao.queryById(storeId);
    }

    /**
     * 分页查询
     *
     * @param storesName 筛选条件
     * @param page 当前页
     * @param limit 记录数
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(String storesName, long page, long limit) {
        long total = this.storesDao.count(storesName);
        page=(page-1)*limit;
        List<Stores> storesList = storesDao.queryAllByLimit(storesName, page, limit);
        Map<String, Object> storesMap = new HashMap<>();
        storesMap.put("storesList",storesList);
        storesMap.put("page",page);
        storesMap.put("limit",limit);
        storesMap.put("total",total);
        return storesMap;
    }

    /**
     * 新增数据
     *
     * @param stores 实例对象
     * @return 实例对象
     */
    @Override
    public Stores insert(Stores stores) {
        storesDao.insert(stores);
        return stores;
    }

    /**
     * 修改数据
     *
     * @param stores 实例对象
     * @return 实例对象
     */
    @Override
    public Stores update(Stores stores) {
        this.storesDao.update(stores);
        return this.queryById(stores.getStoreId());
    }

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer storeId) {
        return this.storesDao.deleteById(storeId) > 0;
    }
}
