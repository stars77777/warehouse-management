package com.warehouseManag.service;

import com.warehouseManag.entity.InboundLedger;
import com.warehouseManag.vo.GoodsStoresVO;
import com.warehouseManag.vo.InboundLedgerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (InboundLedger)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 15:07:16
 */
public interface InboundLedgerService {

    /**
     * 通过ID查询单条数据
     *
     * @param ledgerId 主键
     * @return 实例对象
     */
    InboundLedger queryById(Integer ledgerId);

    /**
     * 分页查询
     *
     * @param inboundLedgerVO 筛选条件
     * @param page      分页对象
     * @param limit      记录数
     * @return 查询结果
     */
    Map<String, Object> queryByPage(InboundLedgerVO inboundLedgerVO, long page, long limit);

    /**
     * 新增数据
     *
     * @param goodsStoresVO 实例对象
     * @return 实例对象
     */
    Map<String,Object> insert(GoodsStoresVO goodsStoresVO);

    /**
     * 修改数据
     *
     * @param inboundLedger 实例对象
     * @return 实例对象
     */
    InboundLedger update(InboundLedger inboundLedger);

    /**
     * 通过主键删除数据
     *
     * @param ledgerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer ledgerId);

}
