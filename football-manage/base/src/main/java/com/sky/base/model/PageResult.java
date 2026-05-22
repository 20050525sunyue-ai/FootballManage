package com.sky.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>分页列表的统一数据结构，作为 {@link Result#getData()} 的泛型实参使用。</p>
 *
 * <p><b>嵌套在 Result 中的 JSON 示例：</b></p>
 * <pre>
 * {
 *   "code": 200,
 *   "message": "success",
 *   "data": {
 *     "records": [ ... ],
 *     "total": 100,
 *     "pageNum": 1,
 *     "pageSize": 10
 *   }
 * }
 * </pre>
 *
 * <p><b>字段语义：</b></p>
 * <ul>
 *   <li>{@code records} — 当前页数据列表（可为空列表，表示无数据但请求合法）</li>
 *   <li>{@code total} — 符合条件的总记录数（跨页总数，非当前页条数）</li>
 *   <li>{@code pageNum} — 当前页码，约定从 {@code 1} 开始（与 PageHelper 默认一致）</li>
 *   <li>{@code pageSize} — 每页条数</li>
 * </ul>
 *
 * <p><b>构建方式：</b>可使用 {@link Builder} 手写；若使用 PageHelper，可在 sky-server 模块通过
 * {@code PageResults.from(PageInfo)}（工具类）由 {@code PageInfo} 转为 {@code PageResult}，避免字段漏填。</p>
 *
 * @param <T> 单条记录的类型，如实体 VO / DTO
 * @see Result
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    /**
     * 当前页记录集合。
     */
    private List<T> records;

    /**
     * 总记录数（分页查询条件下全部匹配的条数）。
     */
    private long total;

    /**
     * 当前页码，从 1 开始。
     */
    private int pageNum;

    /**
     * 每页大小。
     */
    private int pageSize;


}
