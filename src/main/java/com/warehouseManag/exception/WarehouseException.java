package com.warehouseManag.exception;

import lombok.Data;

/**
 * 自定义异常
 * @author cbw769651789
 */
@Data
public class WarehouseException extends RuntimeException{
    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public WarehouseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
