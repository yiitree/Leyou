package com.leyou.common.vo;

import lombok.Data;

import java.util.List;

/**
 * 返回页面的pojo类   vo--view pojo
 * @param <T>
 */
@Data
public class PageResult<T> {

    private Long total;     // 总条数
    private Long totalPage; // 总页数
    private List<T> items;  // 当前页的数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

}