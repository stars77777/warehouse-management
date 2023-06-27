package com.warehouseManag.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OutboundLedger)实体类
 *
 * @author makejava
 * @since 2023-06-27 15:07:38
 */
public class OutboundLedger implements Serializable {
    private static final long serialVersionUID = 664895874182627397L;
    
    private Integer ledgerId;
    
    private Integer goodsId;
    
    private Integer storeId;
    
    private Integer quantity;
    
    private Date outboundDate;


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

    public Date getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(Date outboundDate) {
        this.outboundDate = outboundDate;
    }

}

