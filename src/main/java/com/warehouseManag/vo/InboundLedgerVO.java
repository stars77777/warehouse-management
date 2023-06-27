package com.warehouseManag.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class InboundLedgerVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;

    //商品id
    private Integer goodsId;

    //入库日期
    private Date inboundDate;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(Date inboundDate) {
        this.inboundDate = inboundDate;
    }
}
