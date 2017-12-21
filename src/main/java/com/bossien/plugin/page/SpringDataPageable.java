package com.bossien.plugin.page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/18.
 */
public class SpringDataPageable implements Serializable, Pageable {

    // 当前页
    private Integer pageNumber = 1;

    // 当前页面条数
    private Integer pageSize = 10;

    // 排序条件
    private Sort sort;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    public Sort getSort() {
        return sort;
    }

    public Pageable next() {
        return null;
    }

    public Pageable previousOrFirst() {
        return null;
    }

    public Pageable first() {
        return null;
    }

    public boolean hasPrevious() {
        return false;
    }



    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
