package com.warehouseManag.controller;

import com.warehouseManag.entity.GoodsStores;
import com.warehouseManag.service.GoodsStoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GoodsStores)表控制层
 *
 * @author makejava
 * @since 2023-06-27 21:09:31
 */
@Api(tags = "商品门店关系")
@RestController
@RequestMapping("goodsStores")
public class GoodsStoresController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsStoresService goodsStoresService;

    /**
     * 分页查询
     *
     * @param goodsStores 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public ResponseEntity<Page<GoodsStores>> queryByPage(GoodsStores goodsStores, PageRequest pageRequest) {
        return ResponseEntity.ok(this.goodsStoresService.queryByPage(goodsStores, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("id查询")
    @GetMapping("{id}")
    public ResponseEntity<GoodsStores> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.goodsStoresService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goodsStores 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping
    public ResponseEntity<GoodsStores> add(GoodsStores goodsStores) {
        return ResponseEntity.ok(this.goodsStoresService.insert(goodsStores));
    }

    /**
     * 编辑数据
     *
     * @param goodsStores 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PutMapping
    public ResponseEntity<GoodsStores> edit(GoodsStores goodsStores) {
        return ResponseEntity.ok(this.goodsStoresService.update(goodsStores));
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
        return ResponseEntity.ok(this.goodsStoresService.deleteById(id));
    }

}

