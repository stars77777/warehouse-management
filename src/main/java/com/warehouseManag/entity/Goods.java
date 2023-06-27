package com.warehouseManag.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * (Goods)实体类
 *
 * @author makejava
 * @since 2023-06-27 15:06:11
 */
public class Goods implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;
    
    private Integer goodsId;
    
    private String name;
    
    private String specification;
    
    private Double price;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

