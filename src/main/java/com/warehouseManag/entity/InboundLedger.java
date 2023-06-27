package com.warehouseManag.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (InboundLedger)实体类
 *
 * @author makejava
 * @since 2023-06-27 15:07:16
 */
public class InboundLedger implements Serializable {
    private static final long serialVersionUID = -11906531455880493L;
    
    private Integer ledgerId;
    
    private Integer goodsId;
    
    private Integer storeId;
    
    private Integer quantity;

    //入库日期
    private Date inboundDate;


    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
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

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }

}

