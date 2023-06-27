package com.warehouseManag.dao;

import com.warehouseManag.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-28 00:01:59
 */
@Mapper
public interface MessageDao {

    /**
     *
     * 查询消息哪些超时了
     *
     * @param overThreeTime 当前时间减去三小时
     * @return 查询结果
     */
    List<Message> selectOvertime(@Param("overThreeTime") Date overThreeTime);

    /**
     * 用户查看信息后，前端发送请求参数是信息id
     * 通过ID修改信息阅读状态
     *
     * @param id 主键
     */
    void updateToStatus(@Param("id") Integer id);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param message 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Message> queryAllByLimit(Message message, @Param("pageable") Pageable pageable);

    /**
     * 查询指定行数据
     *
     * @param afterOutTime 出库时间之后
     * @param beforeOutTime 出库时间之前
     * @param page 当前页
     * @param limit 记录数
     * @return 对象列表
     */
    List<Message> queryOutTimeByLimit(@Param("afterOutTime") Date afterOutTime,@Param("beforeOutTime") Date beforeOutTime,@Param("page") long page,@Param("limit") long limit);
    /**
     * 统计总行数
     *
     * @param message 查询条件
     * @return 总行数
     */
    long count(Message message);

    /**
     * 统计总行数
     *
     * @param afterOutTime 出库时间之后
     * @param beforeOutTime 出库时间之前
     * @return 总行数
     */
    long countByOutTime(@Param("afterOutTime") Date afterOutTime,@Param("beforeOutTime") Date beforeOutTime);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int insert(Message message);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Message> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Message> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Message> entities);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 影响行数
     */
    int update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

