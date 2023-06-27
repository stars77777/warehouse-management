package com.warehouseManag.service.impl;

import com.warehouseManag.dao.GoodsDao;
import com.warehouseManag.dao.GoodsStoresDao;
import com.warehouseManag.entity.Goods;
import com.warehouseManag.entity.GoodsStores;
import com.warehouseManag.entity.InboundLedger;
import com.warehouseManag.dao.InboundLedgerDao;
import com.warehouseManag.service.GoodsService;
import com.warehouseManag.service.GoodsStoresService;
import com.warehouseManag.service.InboundLedgerService;
import com.warehouseManag.vo.GoodsStoresVO;
import com.warehouseManag.vo.InboundLedgerVO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (InboundLedger)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 15:07:16
 */
@Service
public class InboundLedgerServiceImpl implements InboundLedgerService {
    @Resource
    private InboundLedgerDao inboundLedgerDao;

    @Resource
    private GoodsStoresDao goodsStoresDao;

    @Resource
    private GoodsDao goodsDao;
    /**
     * 通过ID查询单条数据
     *
     * @param ledgerId 主键
     * @return 实例对象
     */
    @Override
    public InboundLedger queryById(Integer ledgerId) {
        return this.inboundLedgerDao.queryById(ledgerId);
    }

    /**
     * 分页查询
     *
     * @param inboundLedgerVO 筛选条件
     * @param page      当前页
     * @param limit      记录数
     * @return 查询结果
     */
    @Override
    public Map<String, Object> queryByPage(InboundLedgerVO inboundLedgerVO, long page, long limit) {
        long total = this.goodsStoresDao.countByGoodsId(inboundLedgerVO.getGoodsId(),inboundLedgerVO.getInboundDate());
        page=(page-1)*limit;
        List<GoodsStores> goodsStoresList = goodsStoresDao.queryGoodsByLimit(inboundLedgerVO.getGoodsId(),inboundLedgerVO.getInboundDate(), page, limit);
        //1 算出总数量
        int sumQuantity=0;
        for (GoodsStores goodsStores:goodsStoresList){
            sumQuantity=sumQuantity+goodsStores.getQuantity();
        }
        //2 求总金额
        //2.1 查单价
        Goods goods = goodsDao.queryById(inboundLedgerVO.getGoodsId());
        //2.2 总金额
        String sumPrice= String.valueOf(goods.getPrice()*sumQuantity);
        Map<String, Object> goodsStoresMap = new HashMap<>();
        goodsStoresMap.put("goodsStoresList",goodsStoresList);
        goodsStoresMap.put("page",page);
        goodsStoresMap.put("limit",limit);
        goodsStoresMap.put("total",total);
        goodsStoresMap.put("goodsQuantity",sumQuantity);
        goodsStoresMap.put("sumPrice",sumPrice);
        return goodsStoresMap;
    }

    /**
     * 入库
     *
     * @param goodsStoresVO 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> insert(GoodsStoresVO goodsStoresVO) {

        //1、新增入库台账表
        InboundLedger inboundLedger = new InboundLedger();
        inboundLedger.setGoodsId(goodsStoresVO.getGoodsId());
        inboundLedger.setStoreId(goodsStoresVO.getStoreId());
        inboundLedger.setQuantity(goodsStoresVO.getQuantity());
        // 获取当前系统时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        // 将格式化后的字符串转换为Date类型
        Date createTime = java.sql.Timestamp.valueOf(formattedTime);
        inboundLedger.setInboundDate(createTime);
        this.inboundLedgerDao.insert(inboundLedger);

        //2、新增商品门店关系表
        int quantity=0;
        //判断是否存在此商品和门店
        if (goodsStoresDao.checkCount(goodsStoresVO.getGoodsId(), goodsStoresVO.getStoreId())<1)
        {
            //不存在
            GoodsStores goodsStores1 = new GoodsStores();
            goodsStores1.setGoodsId(goodsStoresVO.getGoodsId());
            goodsStores1.setStoreId(goodsStoresVO.getStoreId());
            goodsStores1.setQuantity(goodsStoresVO.getQuantity());
            //2.1在商品表查询当前商品价格
            Goods goods = goodsDao.queryById(goodsStoresVO.getGoodsId());
            Double goodsPrice = goods.getPrice();
            //2.2计算总价
            double sum = goodsPrice * goodsStores1.getQuantity();
            goodsStores1.setSumPrice(sum);
            //2.3 新增
            goodsStoresDao.insert(goodsStores1);
        }else {
            //存在
            //2.1在商品表查询当前商品价格
            Goods goods = goodsDao.queryById(goodsStoresVO.getGoodsId());
            Double goodsPrice = goods.getPrice();
            //2.2查询现在的存货
            GoodsStores oldGoodsStores = goodsStoresDao
                    .queryByIds(goodsStoresVO.getGoodsId(), goodsStoresVO.getStoreId());
            //2.3计算总价
            double sum = goodsPrice * (goodsStoresVO.getQuantity()+oldGoodsStores.getQuantity());
            oldGoodsStores.setSumPrice(sum);
            oldGoodsStores.setQuantity(oldGoodsStores.getQuantity()+ goodsStoresVO.getQuantity());
            //2.4 修改
            goodsStoresDao.update(oldGoodsStores);
            quantity=oldGoodsStores.getQuantity();
        }

        //3、返回当前商品，当前门店 现存数量
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("goods", goodsStoresVO.getGoodsId());
        hashMap.put("store", goodsStoresVO.getStoreId());
        hashMap.put("quantity",quantity);
        return hashMap;
    }

    /**
     * 修改数据
     *
     * @param inboundLedger 实例对象
     * @return 实例对象
     */
    @Override
    public InboundLedger update(InboundLedger inboundLedger) {
        this.inboundLedgerDao.update(inboundLedger);
        return this.queryById(inboundLedger.getLedgerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ledgerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer ledgerId) {
        return this.inboundLedgerDao.deleteById(ledgerId) > 0;
    }
}
