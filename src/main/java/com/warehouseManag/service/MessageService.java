package com.warehouseManag.service;

import com.warehouseManag.entity.Message;
import com.warehouseManag.vo.MessageVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (Message)表服务接口
 *
 * @author makejava
 * @since 2023-06-28 00:01:59
 */
public interface MessageService {

    /**
     *
     *
     * @param
     * @return
     */


    /**
     *
     * 查询消息哪些超时了
     *
     * @param overThreeTime 当前时间减去三小时
     * @return 查询结果
     */
    List<Message> selectOvertime(Date overThreeTime);

    /**
     * 用户查看信息后，前端发送请求参数是信息id
     * 通过ID修改信息阅读状态
     *
     * @param id 主键
     */
    void updateToStatus(Integer id);

    /**
     * 分页查询
     *
     * @param messageVO 筛选条件
     * @param page      当前页
     * @param limit     每页记录数
     * @return 查询结果
     */
    Map<String,Object> queryByPage(MessageVO messageVO, long page, long limit);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Message queryById(Integer id);

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message insert(Message message);

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    Message update(Message message);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
