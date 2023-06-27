package com.warehouseManag.vo;

import java.io.Serial;
import java.io.Serializable;

public class OutboundLedgerVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;
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
}
