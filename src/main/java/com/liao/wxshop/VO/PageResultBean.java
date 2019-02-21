package com.liao.wxshop.VO;

import java.util.List;

public class PageResultBean<T> {

    /**
     * 默认单页记录数量
     */
    public static final int DEFAULT_PAGESIZE = 10;
    /**
     * 默认当前页
     */
    public static final int DEFAULT_CURRENTPAGE = 1;
    /**
     * 单页记录数量
     */
    private int pageSize = DEFAULT_PAGESIZE;
    /**
     * 当前页
     */
    private int currentPage = DEFAULT_CURRENTPAGE;
    /**
     * 总记录数
     */
    private long totalResult = 0;
    /**
     * 返回Object数据
     */
    private Object obj;
    /**
     * true，表示此次操作成功;false,表示失败
     */
    private boolean success = true;
    /**
     * 返回消息，成功或者失败的详细信息
     */
    private String message;
    /**
     * 返回数据集合
     */
    private List<?> list;

    public PageResultBean() {
    }

    public PageResultBean(boolean success) {
        super();
        this.success = success;
    }

    public PageResultBean(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public PageResultBean(Object obj, List<?> list) {
        super();
        this.obj = obj;
        this.list = list;
    }

    public PageResultBean(int currentPage, int pageSize, long totalResult,
                      List<?> list) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalResult = totalResult;
        this.list = list;
    }

    /**
     * 获得总页数
     *
     * @return 总页数
     */
    public long getPageCount() {
        long count = getTotalResult() / getPageSize();
        if (getTotalResult() % getPageSize() > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页
     *
     * @return 是否还有下一页
     */
    public boolean isHasNextPage() {
        return (getCurrentPage() + 1 <= getPageCount());
    }

    /**
     * 获得下页的页号
     *
     * @return 下页的页号
     */
    public int getNextPage() {
        if (isHasNextPage()) {
            return getCurrentPage() + 1;
        } else {
            return getCurrentPage();
        }
    }

    /**
     * 是否有上一页
     *
     * @return 是否有上一页
     */
    public boolean isHasPrePage() {
        return (getCurrentPage() - 1 >= 1);
    }

    /**
     * 返回上页的页号
     *
     * @return 上页的页号
     */
    public int getPrePage() {
        if (isHasPrePage()) {
            return getCurrentPage() - 1;
        } else {
            return getCurrentPage();
        }
    }

    /**
     * 计算起始记录
     *
     * @param currentPage 当前页
     * @param pageSize    单页记录数量
     * @return 起始记录号
     */
    public static int getFirstResult(int currentPage, int pageSize) {
        if (currentPage < 0 || pageSize < 0) {
            return -1;
        } else {
            return ((currentPage - 1) * pageSize);
        }
    }

    /**
     * 获得单页记录数量
     *
     * @return 单页记录数量
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置单页记录数量
     *
     * @param pageSize 单页记录数量
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得当前页
     *
     * @return 当前页
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页
     *
     * @param currentPage 当前页
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获得总记录数
     *
     * @return 总记录数
     */
    public long getTotalResult() {
        return totalResult;
    }

    /**
     * 设置总记录数
     *
     * @param totalResult 总记录数
     */
    public void setTotalResult(long totalResult) {
        this.totalResult = totalResult;
    }
}