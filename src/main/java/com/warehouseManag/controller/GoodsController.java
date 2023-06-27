package com.warehouseManag.controller;

import com.warehouseManag.entity.Goods;
import com.warehouseManag.service.GoodsService;
import com.warehouseManag.vo.GoodsVO;
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
 * (Goods)表控制层
 *
 * @author makejava
 * @since 2023-06-27 15:06:06
 */
@Api(tags = "商品接口")
@RestController
@RequestMapping("goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    /**
     * 分页查询
     *
     * @param goodsVO 筛选条件
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
                                           @ApiParam(name = "goodsVO", value = "查询对象", required = false)
                                           GoodsVO goodsVO) {
        return this.goodsService.queryByPage(goodsVO.getGoodsName(), page,limit);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "id查询接口")
    @GetMapping("{id}")
    public ResponseEntity<Goods> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.goodsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param goods 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增商品接口")
    @PostMapping
    public ResponseEntity<Goods> add(Goods goods) {
        return ResponseEntity.ok(this.goodsService.insert(goods));
    }

    /**
     * 编辑数据
     *
     * @param goods 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑商品接口")
    @PutMapping
    public ResponseEntity<Goods> edit(Goods goods) {
        return ResponseEntity.ok(this.goodsService.update(goods));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除商品接口")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.goodsService.deleteById(id));
    }

}

