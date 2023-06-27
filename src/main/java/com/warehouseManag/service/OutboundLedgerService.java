package com.warehouseManag.service;

import com.warehouseManag.entity.OutboundLedger;
import com.warehouseManag.vo.OutboundLedgerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (OutboundLedger)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 15:07:38
 */
public interface OutboundLedgerService {

    /**
     * 通过ID查询单条数据
     *
     * @param ledgerId 主键
     * @return 实例对象
     */
    OutboundLedger queryById(Integer ledgerId);

    /**
     * 分页查询
     *
     * @param outboundLedger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<OutboundLedger> queryByPage(OutboundLedger outboundLedger, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param outboundLedgerVO 实例对象
     * @return 实例对象
     */
    Map<String,Object> insert(OutboundLedgerVO outboundLedgerVO);

    /**
     * 修改数据
     *
     * @param outboundLedger 实例对象
     * @return 实例对象
     */
    OutboundLedger update(OutboundLedger outboundLedger);

    /**
     * 通过主键删除数据
     *
     * @param ledgerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer ledgerId);

}
