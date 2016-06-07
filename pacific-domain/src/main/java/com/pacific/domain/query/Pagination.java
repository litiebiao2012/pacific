package com.pacific.domain.query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Fe on 16/3/14.
 */
public class Pagination<T> {
    /**
     * 当前页，从1开始
     */
    private int currentPage = 1;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    /**
     * results
     */
    private List<T> data;

    private String result;

    private int totalCount;



    @SuppressWarnings({ "rawtypes", "unchecked" }) private final static Pagination EMPTY = new Pagination(0, 0, Collections.emptyList());

    @SuppressWarnings("unchecked") public static <T> Pagination<T> emptyPaginator() {
        return EMPTY;
    }

    public final static int TotalCountNotSupported = -1;

    public Pagination(int pageSize, int currentPage, List<T> data) {
        this.setPageSize(pageSize);
        this.setCurrentPage(currentPage);
        this.setData(data);
        this.setTotalCount(TotalCountNotSupported);
    }

    public Pagination(BaseQuery baseQuery, Integer totalCount) {
        this(baseQuery.getPageSize(), baseQuery.getCurrentPage(), null, totalCount);
    }


    public Pagination(BaseQuery baseQuery, List<T> data, Integer totalCount) {
        this(baseQuery.getPageSize(), baseQuery.getCurrentPage(), data, totalCount);
    }

    public Pagination(int pageSize, int currentPage, List<T> data, Integer totalCount) {
        this.setPageSize(pageSize);
        this.setCurrentPage(currentPage);
        this.setData(data);
        if (totalCount == null) {
            totalCount = 0;
        }
        this.setTotalCount(totalCount);

    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean getHasNext() {
        return totalCount / pageSize > currentPage;
    }

    public boolean getIsEmpty() {
        return CollectionUtils.isEmpty(getData());
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 总共多少页
     *
     * @return
     */
    public int getTotalPage() {
        int totalCount = this.getTotalCount();
        if (totalCount != TotalCountNotSupported) {
            int page = totalCount / this.getPageSize();
            page += totalCount - this.getPageSize() * page > 0 ? 1 : 0;
            return page;
        } else {
            return TotalCountNotSupported;
        }

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
