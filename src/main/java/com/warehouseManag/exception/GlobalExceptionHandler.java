package com.warehouseManag.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * @author cbw769651789
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(WarehouseException.class)
    @ResponseBody
    public Map<String,Object> error(WarehouseException e){
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("message",e.getMessage());
        return map;
    }
}
