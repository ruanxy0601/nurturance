package com.ruanxy.common.base;

import cn.hutool.core.util.PageUtil;
import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能概要：
 *
 * @param <T> the type parameter
 */
public class PageResult<T> extends Pagination {

    private static final long serialVersionUID = 1L;

    private List<T> dataList = new ArrayList<T>();

    private long total;

    private int pages;

    /**
     * To page result.
     *
     * @param <T>      the type parameter
     * @param dataList the  dataList
     * @return the page result
     */
    public static <T> PageResult<T> toPageResult(List<T> dataList) {
        PageResult<T> result = new PageResult<T>();

        if (dataList instanceof Page) {
            Page<T> page = (Page<T>) dataList;
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        } else {
            result.setPageNum(1);
            result.setPageSize(dataList.size());
            result.setDataList(dataList);
            result.setTotal(dataList.size());
        }

        return result;
    }

    /**
     * Gets data list.
     *
     * @return the data list
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * Sets data list.
     *
     * @param dataList the data list
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * Gets pages.
     *
     * @return the pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Sets pages.
     *
     * @param pages the pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * Get offset index int.
     *
     * @return the int
     */
    public int getOffsetIndex() {
        int offset = (getPageNum() - 1) * getPageSize();
        if (offset >= getTotal()) {
            offset = 0;
        }
        return offset;
    }

//    /**
//     * To page info page info.
//     *
//     * @param <T>   the type parameter
//     * @param datas the datas
//     * @return the page info
//     */
//    public static <T> PageInfo toPageInfo(List<T> datas) {
//        PageInfo page = new PageInfo(datas);
//
//        return page;
//    }

    /**
     * 转化分页信息
     * 取pageInfo中的分页、排序信息，将其转换成T类型的PageResult
     *
     * @param pageInfo 原分页
     * @param dataList 数据
     * @param <T>      泛型
     * @return 结果
     */
    public static <T> PageResult<T> toPageResult(PageResult<?> pageInfo, List<T> dataList) {
        PageResult<T> result = new PageResult<>();
        result.setDataList(dataList);
        result.setPages(pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        result.setSortField(pageInfo.getSortField());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setSortOrder(pageInfo.getSortOrder());
        return result;
    }

    /**
     * 通过页码与每页最大记录数对数据集合进行分页，返回pageResult
     *
     * @param pageNum  页码
     * @param pageSize 每页最大记录数
     * @param dataList 数据集合
     * @param <T>      泛性
     * @return pageResult
     */
    public static <T> PageResult<T> paging(int pageNum, int pageSize, List<T> dataList) {
        // 总记录数默认为0
        int total = 0;
        // 如果返回数据不为空
        if (CollectionUtils.isNotEmpty(dataList)) {
            // 总数为数据集合长度
            total = dataList.size();
            // 获取最大页数
            int totalPage = PageUtil.totalPage(total, pageSize);
            // 如果入参页码>最大页数则当前页码为最大页数
            if (pageNum > totalPage) {
                pageNum = totalPage;
            }
            // 获取对数据集合截取的开始索引与结束索引
            int[] startWithEnd = PageUtil.transToStartEnd(pageNum, pageSize);
            int beginIndex = startWithEnd[0];
            // 结束索引
            int endIndex = startWithEnd[1];
            // 如果入参每页最大记录数>总记录数或结束索引>总记录数则结束索引为总记录数
            if (pageSize > total || endIndex > total) {
                pageNum = 1;
                beginIndex = 0;
                endIndex = total;
            }
            dataList = dataList.subList(beginIndex, endIndex);
        }
        int pages = PageUtil.totalPage(total, pageSize);
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);
        pageResult.setPages(pages);
        pageResult.setDataList(dataList);
        return pageResult;
    }

}
