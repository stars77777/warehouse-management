package com.warehouseManag.dao;

import com.warehouseManag.entity.InboundLedger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (InboundLedger)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-27 15:07:16
 */
@Mapper
public interface InboundLedgerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param ledgerId 主键
     * @return 实例对象
     */
    InboundLedger queryById(Integer ledgerId);

    /**
     * 查询指定行数据
     *
     * @param inboundLedger 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<InboundLedger> queryAllByLimit(InboundLedger inboundLedger, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param inboundLedger 查询条件
     * @return 总行数
     */
    long count(InboundLedger inboundLedger);

    /**
     * 新增数据
     *
     * @param inboundLedger 实例对象
     * @return 影响行数
     */
    int insert(InboundLedger inboundLedger);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<InboundLedger> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<InboundLedger> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<InboundLedger> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<InboundLedger> entities);

    /**
     * 修改数据
     *
     * @param inboundLedger 实例对象
     * @return 影响行数
     */
    int update(InboundLedger inboundLedger);

    /**
     * 通过主键删除数据
     *
     * @param ledgerId 主键
     * @return 影响行数
     */
    int deleteById(Integer ledgerId);

}

