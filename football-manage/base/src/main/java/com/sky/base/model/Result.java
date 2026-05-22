package com.sky.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>统一 REST 响应体，与前端 Axios / 网关约定对齐。</p>
 *
 * <p><b>JSON 形态示例（成功）：</b></p>
 * <pre>
 * {
 *   "code": 200,
 *   "message": "success",
 *   "data": { ... }
 * }
 * </pre>
 *
 * <p><b>字段语义：</b></p>
 * <ul>
 *   <li>{@code code} — 业务状态码（<b>注意</b>：可与 HTTP 状态码含义相近但不强行相等）。常用 {@code 200} 表示成功；
 *       {@code 401} 表示未登录或 Token 失效；其它值表示各类业务或校验失败。</li>
 *   <li>{@code message} — 给人读的提示文案，成功时多为 {@code "success"}。</li>
 *   <li>{@code data} — 实际载荷；无数据时可省略或由序列化为 {@code null}，分页列表时应嵌套 {@link PageResult}。</li>
 * </ul>
 *
 * <p><b>与 HTTP 状态码的配合：</b>拦截器等可在返回 HTTP {@code 401} 的同时，仍将响应体序列化为本结构且 {@code code=401}；
 * 业务异常是否使用 HTTP 200 + body 内非 200 的 {@code code}，由项目统一规范决定。</p>
 *
 * @param <T> {@code data} 的类型，例如单个 {@code UserVO}、{@link PageResult}{@code <UserVO>}、或 {@link Void} 场景下的占位
 * @see PageResult
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 业务状态码。
     * <ul>
     *   <li>{@code 200} — 成功</li>
     *   <li>{@code 401} — 未登录 / Token 无效或过期（常与 HTTP 401 同时使用）</li>
     *   <li>{@code 400} — 通用客户端错误（{@link #error(String)} 默认值）</li>
     *   <li>其它 — 按模块自定义（建议在常量类中集中定义）</li>
     * </ul>
     */
    private Integer code;

    /**
     * 提示信息：错误时为错误描述；成功时建议使用固定文案 {@code success} 以保持前端判断简单。
     */
    private String message;

    /**
     * 业务数据载荷。
     * <ul>
     *   <li>列表分页：使用 {@link PageResult} 作为本字段的类型参数实例</li>
     *   <li>仅状态无数据：可用 {@link #success()}，{@code data} 可为 null</li>
     * </ul>
     */
    private T data;

    /**
     * 成功且无 {@code data}（例如仅表示「操作完成」）。
     *
     * @param <T> 泛型占位，便于调用处写出 {@code Result<Void>} 等
     * @return {@code code=200}, {@code message=success}, {@code data=null}
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "success";
        return result;
    }

    /**
     * 成功并携带载荷。
     *
     * @param object 写入 {@code data} 的对象，不可为 success 语义时再包一层 Result
     * @param <T>    data 类型
     * @return {@code code=200}, {@code message=success}, {@code data=object}
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.code = 200;
        result.message = "success";
        result.data = object;
        return result;
    }

    /**
     * 失败：默认 {@code code=400}。
     * <p>若需与文档一致返回 {@code 401}，请使用 {@link #error(int, String)}。</p>
     *
     * @param message 错误提示（对应 JSON {@code message}）
     */
    public static <T> Result<T> error(String message) {
        return error(400, message);
    }

    /**
     * 失败并指定业务状态码。
     *
     * @param code    业务码，如 {@code 401}
     * @param message 错误提示
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message != null ? message : "";
        return result;
    }

}
