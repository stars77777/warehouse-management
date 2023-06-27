package com.warehouseManag.service.impl;

import com.warehouseManag.entity.*;
import com.warehouseManag.dao.OutboundLedgerDao;
import com.warehouseManag.exception.WarehouseException;
import com.warehouseManag.service.GoodsService;
import com.warehouseManag.service.GoodsStoresService;
import com.warehouseManag.service.MessageService;
import com.warehouseManag.service.OutboundLedgerService;
import com.warehouseManag.vo.OutboundLedgerVO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * (OutboundLedger)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 15:07:38
 */
@Service
public class OutboundLedgerServiceImpl implements OutboundLedgerService {
    @Resource
    private OutboundLedgerDao outboundLedgerDao;

    @Resource
    private GoodsStoresService goodsStoresService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private MessageService messageService;

    /**
     * 通过ID查询单条数据
     *
     * @param ledgerId 主键
     * @return 实例对象
     */
    @Override
    public OutboundLedger queryById(Integer ledgerId) {
        return this.outboundLedgerDao.queryById(ledgerId);
    }

    /**
     * 分页查询
     *
     * @param outboundLedger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<OutboundLedger> queryByPage(OutboundLedger outboundLedger, PageRequest pageRequest) {
        long total = this.outboundLedgerDao.count(outboundLedger);
        return new PageImpl<>(this.outboundLedgerDao.queryAllByLimit(outboundLedger, pageRequest), pageRequest, total);
    }

    /**
     * 出库
     *
     * @param outboundLedgerVO 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,Object> insert(OutboundLedgerVO outboundLedgerVO) {
        GoodsStores oldGoodsStores = goodsStoresService
                .queryByIds(outboundLedgerVO.getGoodsId(), outboundLedgerVO.getStoreId());
        if (outboundLedgerVO.getQuantity()>oldGoodsStores.getQuantity()){
            throw new WarehouseException("库存不足",200);
        }
        //1、新增出库台账表
        OutboundLedger outboundLedger = new OutboundLedger();
        outboundLedger.setGoodsId(outboundLedgerVO.getGoodsId());
        outboundLedger.setStoreId(outboundLedgerVO.getStoreId());
        outboundLedger.setQuantity(outboundLedgerVO.getQuantity());
        // 获取当前系统时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        // 将格式化后的字符串转换为Date类型
        Date createTime = java.sql.Timestamp.valueOf(formattedTime);
        outboundLedger.setOutboundDate(createTime);
        this.outboundLedgerDao.insert(outboundLedger);

        //2、新增商品门店关系表
            //存在
            //2.1在商品表查询当前商品价格
            Goods goods = goodsService.queryById(outboundLedgerVO.getGoodsId());
            Double goodsPrice = goods.getPrice();
            //2.2查询现在的存货

            //2.3计算总价
            double sum = goodsPrice * (oldGoodsStores.getQuantity()-outboundLedgerVO.getQuantity());
            oldGoodsStores.setSumPrice(sum);
            oldGoodsStores.setQuantity(oldGoodsStores.getQuantity()-outboundLedgerVO.getQuantity());
            //2.4 修改
            goodsStoresService.update(oldGoodsStores);

        //3、返回当前商品，当前门店 现存数量
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("goods",outboundLedgerVO.getGoodsId());
        hashMap.put("store",outboundLedgerVO.getStoreId());
        hashMap.put("quantity",outboundLedger.getQuantity());

        //4、发送消息给admin
        Message message = new Message();
        message.setIdentity("admin");
        message.setOutTime(outboundLedger.getOutboundDate());
        message.setContent("商品"+outboundLedgerVO.getGoodsId()+"在门店"+outboundLedgerVO.getStoreId()+"出库了，数量："+outboundLedgerVO.getQuantity()+"个");
        message.setReadStatus(0);
        message.setCreateTime(createTime);
        messageService.insert(message);
        return hashMap;
    }



    /**
     * 修改数据
     *
     * @param outboundLedger 实例对象
     * @return 实例对象
     */
    @Override
    public OutboundLedger update(OutboundLedger outboundLedger) {
        this.outboundLedgerDao.update(outboundLedger);
        return this.queryById(outboundLedger.getLedgerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ledgerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer ledgerId) {
        return this.outboundLedgerDao.deleteById(ledgerId) > 0;
    }
}
