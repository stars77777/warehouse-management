package com.warehouseManag.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class MessageVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 889912236381586239L;

    /**
     *  出库时间之后
     */
    private Date afterOutTime;

    /**
     *  出库时间之前
     */
    private Date beforeOutTime;

    public Date getAfterOutTime() {
        return afterOutTime;
    }

    public void setAfterOutTime(Date afterOutTime) {
        this.afterOutTime = afterOutTime;
    }

    public Date getBeforeOutTime() {
        return beforeOutTime;
    }

    public void setBeforeOutTime(Date beforeOutTime) {
        this.beforeOutTime = beforeOutTime;
    }
}
