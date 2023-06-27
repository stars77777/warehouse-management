package com.warehouseManag.service.impl;

import com.warehouseManag.entity.Goods;
import com.warehouseManag.entity.Message;
import com.warehouseManag.dao.MessageDao;
import com.warehouseManag.service.MessageService;
import com.warehouseManag.vo.MessageVO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2023-06-28 00:01:59
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Override
    public List<Message> selectOvertime(Date overThreeTime) {
        return messageDao.selectOvertime(overThreeTime);
    }

    /**
     * 用户查看信息后，前端发送请求参数是信息id
     * 通过ID修改信息阅读状态
     *
     * @param id 主键
     */
    @Override
    public void updateToStatus(Integer id) {
        messageDao.updateToStatus(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message queryById(Integer id) {
        return this.messageDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param messageVO 筛选条件
     * @param page      当前页
     * @param limit     每页记录数
     * @return 查询结果
     */
    @Override
    public Map<String,Object> queryByPage(MessageVO messageVO, long page, long limit) {
        long total = this.messageDao.countByOutTime(messageVO.getAfterOutTime(),messageVO.getBeforeOutTime());
        page=(page-1)*limit;
        List<Message> messagesList = messageDao.
                queryOutTimeByLimit(messageVO.getAfterOutTime(),messageVO.getBeforeOutTime(), page, limit);
        Map<String, Object> messagesMap = new HashMap<>();
        messagesMap.put("messagesList",messagesList);
        messagesMap.put("page",page);
        messagesMap.put("limit",limit);
        messagesMap.put("total",total);
        return messagesMap;
    }

    /**
     * 新增数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }

    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.messageDao.deleteById(id) > 0;
    }
}
