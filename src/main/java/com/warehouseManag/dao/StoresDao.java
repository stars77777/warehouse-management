package com.warehouseManag.dao;

import com.warehouseManag.entity.Stores;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Stores)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-27 15:08:06
 */
@Mapper
public interface StoresDao {

    /**
     * 通过ID查询单条数据
     *
     * @param storeId 主键
     * @return 实例对象
     */
    Stores queryById(Integer storeId);

    /**
     * 查询指定行数据
     *
     * @param storesName 查询条件
     * @param page 当前页
     * @param limit 记录数
     * @return 对象列表
     */
    List<Stores> queryAllByLimit(@Param("name") String storesName, @Param("page") long page,@Param("limit") long limit);

    /**
     * 统计总行数
     *
     * @param name 查询条件
     * @return 总行数
     */
    long count(@Param("name") String name);

    /**
     * 新增数据
     *
     * @param stores 实例对象
     * @return 影响行数
     */
    int insert(Stores stores);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Stores> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Stores> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Stores> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Stores> entities);

    /**
     * 修改数据
     *
     * @param stores 实例对象
     * @return 影响行数
     */
    int update(Stores stores);

    /**
     * 通过主键删除数据
     *
     * @param storeId 主键
     * @return 影响行数
     */
    int deleteById(Integer storeId);

}

