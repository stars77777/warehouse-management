package com.warehouseManag.entity;

import java.io.Serializable;

/**
 * (Stores)实体类
 *
 * @author makejava
 * @since 2023-06-27 15:08:06
 */
public class Stores implements Serializable {
    private static final long serialVersionUID = 969881532590815373L;
    
    private Integer storeId;
    
    private String name;


    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

