package com.warehouseManag.controller;

import com.warehouseManag.entity.Message;
import com.warehouseManag.service.MessageService;
import com.warehouseManag.vo.GoodsVO;
import com.warehouseManag.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2023-06-28 00:01:59
 */
@Api(tags = "消息接口")
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 用户查看信息后，前端发送请求参数是信息id
     * 通过ID修改信息阅读状态
     *
     * @param id 主键
     */
    @ApiOperation("id查询修改状态")
    @PutMapping("{id}")
    public void edit(@PathVariable Integer id) {
        messageService.updateToStatus(id);
    }
    /**
     * 分页查询
     *
     * @param messageVO 筛选条件
     * @param page      当前页
     * @param limit     每页记录数
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public Map<String,Object> queryByPage(@ApiParam(name = "page", value = "当前页码", required = true)
                                              @PathVariable Long page,
                                          @ApiParam(name = "limit", value = "每页记录数", required = true)
                                              @PathVariable Long limit,
                                          @ApiParam(name = "goodsVO", value = "查询对象", required = false)
                                              MessageVO messageVO) {
        return this.messageService.queryByPage(messageVO,page,limit);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("id查询")
    @GetMapping("{id}")
    public ResponseEntity<Message> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.messageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param message 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<Message> add(Message message) {
        return ResponseEntity.ok(this.messageService.insert(message));
    }

    /**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public ResponseEntity<Message> edit(Message message) {
        return ResponseEntity.ok(this.messageService.update(message));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除数据")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.messageService.deleteById(id));
    }

}

