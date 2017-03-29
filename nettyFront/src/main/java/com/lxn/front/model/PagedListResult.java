package com.lxn.front.model;

import java.util.List;

/**
 * Created by Lin Xiangnan on 2017/2/24.
 *
 * @author Lin Xiangnan
 * @func
 **/
public class PagedListResult {

    private List list;
    private Long totalNum;
    private Integer currentPage;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
