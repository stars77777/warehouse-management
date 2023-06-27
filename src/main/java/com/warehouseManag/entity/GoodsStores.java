package com.warehouseManag.entity;

import java.io.Serializable;

/**
 * (GoodsStores)实体类
 *
 * @author makejava
 * @since 2023-06-27 21:09:31
 */
public class GoodsStores implements Serializable {
    private static final long serialVersionUID = -24981642761940454L;

    private Integer id;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 总价
     */
    private Double sumPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

}

