package com.ashop.beans;

import java.io.Serializable;

/**
 * 封装客户端发送上架, 下架, 删除商品请求后, 需要响应的数据模型.
 */
public class AshopResult implements Serializable {
    private Integer status; //响应状态
    private Object data;    //响应数据
    private String msg;     //响应消息

    /**
     * 静态方法, 返回AshopResult对象
     */
    public static AshopResult ok(){
        return new AshopResult(null);
    }

    public AshopResult() {
    }

    public AshopResult(Object data) {
        this.data = data;
        this.status = 200;
        this.msg = "ok";
    }

    public AshopResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public AshopResult(Integer status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
