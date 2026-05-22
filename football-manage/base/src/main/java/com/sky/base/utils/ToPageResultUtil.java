package com.sky.base.utils;

import com.github.pagehelper.PageInfo;
import com.sky.base.model.PageResult;

/**
 * PageHelper 分页结果转为统一分页结构 {@link PageResult}
 */
public final class ToPageResultUtil {

    private ToPageResultUtil() {
    }

    public static <T> PageResult<T> from(PageInfo<T> pageInfo) {
        return PageResult.<T>builder()
                .records(pageInfo.getList())
                .total(pageInfo.getTotal())
                .pageNum(pageInfo.getPageNum())
                .pageSize(pageInfo.getPageSize())
                .build();
    }
}
