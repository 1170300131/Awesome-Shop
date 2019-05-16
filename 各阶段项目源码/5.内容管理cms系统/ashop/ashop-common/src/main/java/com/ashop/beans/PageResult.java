package com.ashop.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 封装DataGrid控件需要的数据模型
 */
public class PageResult<T> implements Serializable {
    private List<T> rows;//rows和total是EasyUI的命名规范
    private long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
