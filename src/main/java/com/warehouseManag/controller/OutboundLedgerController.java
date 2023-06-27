package com.warehouseManag.controller;

import com.warehouseManag.entity.OutboundLedger;
import com.warehouseManag.service.OutboundLedgerService;
import com.warehouseManag.vo.OutboundLedgerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (OutboundLedger)表控制层
 *
 * @author makejava
 * @since 2023-06-27 15:07:37
 */
@Api(tags = "出库台账接口")
@RestController
@RequestMapping("outboundLedger")
public class OutboundLedgerController {
    /**
     * 服务对象
     */
    @Resource
    private OutboundLedgerService outboundLedgerService;

    /**
     * 分页查询
     *
     * @param outboundLedger 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询接口")
    @GetMapping
    public ResponseEntity<Page<OutboundLedger>> queryByPage(OutboundLedger outboundLedger, PageRequest pageRequest) {
        return ResponseEntity.ok(this.outboundLedgerService.queryByPage(outboundLedger, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "id查询接口")
    @GetMapping("{id}")
    public ResponseEntity<OutboundLedger> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.outboundLedgerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param outboundLedgerVO 实体
     * @return 新增结果
     */
    @ApiOperation(value = "出库接口")
    @PostMapping
    public Map<String,Object> add(OutboundLedgerVO outboundLedgerVO) {
        return this.outboundLedgerService.insert(outboundLedgerVO);
    }

    /**
     * 编辑数据
     *
     * @param outboundLedger 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑出账信息接口")
    @PutMapping
    public ResponseEntity<OutboundLedger> edit(OutboundLedger outboundLedger) {
        return ResponseEntity.ok(this.outboundLedgerService.update(outboundLedger));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除出账信息接口")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.outboundLedgerService.deleteById(id));
    }

}

