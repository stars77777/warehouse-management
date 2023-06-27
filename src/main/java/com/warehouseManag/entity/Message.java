package com.warehouseManag.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2023-06-28 00:01:59
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 631994580197238907L;
    
    private Integer id;
    /**
     * 用户标识
     */
    private String identity;
    /**
     * 出库时间
     */
    private Date outTime;
    /**
     * 信息文本
     */
    private String content;
    /**
     * 阅读状态（0未读，1已读）
     */
    private Integer readStatus;
    /**
     *  消息创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

