package com.warehouseManag.service.impl;

import com.warehouseManag.entity.Goods;
import com.warehouseManag.dao.GoodsDao;
import com.warehouseManag.entity.Stores;
import com.warehouseManag.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Goods)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 15:06:16
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    @Override
    public Goods queryById(Integer goodsId) {
        return this.goodsDao.queryById(goodsId);
    }

    /**
     * 分页查询
     *
     * @param goodsName 筛选条件
     * @param page      当前页
     * @param limit     每页展示
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(String goodsName, long page, long limit) {
        long total = this.goodsDao.count(goodsName);
        page=(page-1)*limit;
        List<Goods> goodsList = goodsDao.queryAllByLimit(goodsName, page, limit);
        Map<String, Object> goodsMap = new HashMap<>();
        goodsMap.put("storesList",goodsList);
        goodsMap.put("page",page);
        goodsMap.put("limit",limit);
        goodsMap.put("total",total);
        return goodsMap;
    }

    /**
     * 新增数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods insert(Goods goods) {
        // 获取当前系统时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        // 将格式化后的字符串转换为Date类型
        Date createTime = java.sql.Timestamp.valueOf(formattedTime);
        // 设置goods对象的创建时间属性
        goods.setCreateTime(createTime);
        System.out.println(goods.getName());
        this.goodsDao.insert(goods);
        return goods;
    }

    /**
     * 修改数据
     *
     * @param goods 实例对象
     * @return 实例对象
     */
    @Override
    public Goods update(Goods goods) {
        this.goodsDao.update(goods);
        return this.queryById(goods.getGoodsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer goodsId) {
        return this.goodsDao.deleteById(goodsId) > 0;
    }
}
