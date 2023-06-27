package com.warehouseManag.controller;

import com.warehouseManag.entity.InboundLedger;
import com.warehouseManag.service.InboundLedgerService;
import com.warehouseManag.vo.GoodsVO;
import com.warehouseManag.vo.GoodsStoresVO;
import com.warehouseManag.vo.InboundLedgerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (InboundLedger)表控制层
 *
 * @author makejava
 * @since 2023-06-27 15:07:16
 */
@Api(tags = "入库台账接口")
@RestController
@RequestMapping("inboundLedger")
public class InboundLedgerController {
    /**
     * 服务对象
     */
    @Resource
    private InboundLedgerService inboundLedgerService;

    /**
     * 分页查询
     *
     * @param inboundLedgerVO 筛选条件
     * @param page       当前页码
     * @param limit      每页记录数
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询接口")
    @GetMapping("{page}/{limit}")
    public Map<String, Object> queryByPage(@ApiParam(name = "page", value = "当前页码", required = true)
                                               @PathVariable Long page,
                                           @ApiParam(name = "limit", value = "每页记录数", required = true)
                                               @PathVariable Long limit,
                                           @ApiParam(name = "inboundLedgerVO", value = "查询条件", required = false)
                                           InboundLedgerVO inboundLedgerVO) {
        return this.inboundLedgerService.queryByPage(inboundLedgerVO, page,limit);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "id查询接口")
    @GetMapping("{id}")
    public ResponseEntity<InboundLedger> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.inboundLedgerService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goodsStoresVO 实体
     * @return 新增结果
     */
    @ApiOperation(value = "入库接口")
    @PostMapping("insert")
    public Map<String,Object> add(GoodsStoresVO goodsStoresVO) {
        return this.inboundLedgerService.insert(goodsStoresVO);
    }

    /**
     * 编辑数据
     *
     * @param inboundLedger 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑入账信息接口")
    @PutMapping
    public ResponseEntity<InboundLedger> edit(InboundLedger inboundLedger) {
        return ResponseEntity.ok(this.inboundLedgerService.update(inboundLedger));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除入账信息接口")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.inboundLedgerService.deleteById(id));
    }

}

