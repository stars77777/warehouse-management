package com.warehouseManag.controller;

import com.warehouseManag.entity.Stores;
import com.warehouseManag.service.StoresService;
import com.warehouseManag.utils.RestPage;
import com.warehouseManag.vo.StoresVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Stores)表控制层
 *
 * @author makejava
 * @since 2023-06-27 15:08:06
 */
@Api(tags = "门店接口")
@RestController
@RequestMapping("/stores/admin")
@Log4j
public class StoresController {
    /**
     * 服务对象
     */
    @Autowired
    private StoresService storesService;

    /**
     * 分页查询
     *
     * @param storesVO 筛选条件
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
                                           @ApiParam(name = "storesVO", value = "查询对象", required = false)
                                           StoresVO storesVO) {
        return this.storesService.queryByPage(storesVO.getStoresName(), page,limit);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "id查询接口")
    @GetMapping("{id}")
    public ResponseEntity<Stores> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.storesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param stores 实体
     * @return 新增结果
     */
    @ApiOperation(value = "新增门店接口")
    @PostMapping("insert")
    public ResponseEntity<Stores> add(@RequestBody Stores stores) {
        return ResponseEntity.ok(this.storesService.insert(stores));
    }

    /**
     * 编辑数据
     *
     * @param stores 实体
     * @return 编辑结果
     */
    @ApiOperation(value = "编辑门店接口")
    @PutMapping("edit")
    public ResponseEntity<Stores> edit(@RequestBody Stores stores) {
        return ResponseEntity.ok(this.storesService.update(stores));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation(value = "删除门店接口")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.storesService.deleteById(id));
    }

}

