package com.ydgk.eight.util;

/**
 * @author 游斌
 * @create 2020-03-02  11:26
 */
public class Page {
    int pageSize=5;
    int pageNow=1;
    int totalPages;
    int totalCount;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public Page(int pageSize, int pageNow, int totalCount) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
        this.totalCount = totalCount;
    }

    public int getTotalPages() {

        if (totalCount%pageSize==0){

            totalPages= totalCount/pageSize;
        }else {
            totalPages=totalCount/pageSize +1;
        }
        return  totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Page() {

    }

    public Page(int pageSize, int pageNow, int totalPages, int totalCount) {

        this.pageSize = pageSize;
        this.pageNow = pageNow;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
    }
}
