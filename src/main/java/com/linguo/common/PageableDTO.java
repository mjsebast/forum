package com.linguo.common;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageableDTO implements Pageable {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_LIMIT = 10;

    private String query;
    private Integer page;
    private Integer limit;
    private Sort sort;

    public PageableDTO() {
        super();
    }

    public PageableDTO(String query, Integer page, Integer limit, Sort sort) {
        this.query = query;
        this.page = page != null && !(page < 0) ? page : DEFAULT_PAGE;
        this.limit = limit != null ? limit : DEFAULT_LIMIT;
        this.sort = sort;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public int getPageNumber() {
        return page != null ? page : DEFAULT_PAGE;
    }

    @Override
    public int getPageSize() {
        return limit != null ? limit : DEFAULT_LIMIT;
    }

    @Override
    public int getOffset() {
        return page != null && limit != null ? page * limit : 0;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
