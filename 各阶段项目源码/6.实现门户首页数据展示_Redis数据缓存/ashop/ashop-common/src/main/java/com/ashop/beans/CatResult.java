package com.ashop.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 封装响应到前台的数据模型
 */
public class CatResult implements Serializable {
    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
