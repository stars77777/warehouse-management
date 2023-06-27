package com.warehouseManag.vo;

import java.io.Serial;
import java.io.Serializable;

public class GoodsVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    private String goodsName;
}
