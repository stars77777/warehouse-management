package com.warehouseManag.vo;

import java.io.Serial;
import java.io.Serializable;

public class StoresVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;

    private String storesName;

    public String getStoresName() {
        return storesName;
    }

    public void setStoresName(String storesName) {
        this.storesName = storesName;
    }
}
