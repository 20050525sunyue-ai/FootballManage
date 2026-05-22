package com.sky.base.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类，基于 {@link StringRedisTemplate} 封装常用读写。
 * <p>
 * 约定：key 建议带业务前缀，例如 {@code login:token:1001}，避免多模块 key 冲突。
 * 复杂对象统一用 JSON 存入 String 类型；Hash 字段若非字符串，写入时会自动 JSON 序列化。
 */
@Component
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // ========================= String =========================

    /**
     * 写入字符串，永不过期。
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 写入字符串并设置过期时间。
     *
     * @param time 过期时长
     * @param unit 时间单位
     */
    public void set(String key, String value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 读取字符串；key 不存在时返回 {@code null}。
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // ========================= Object（JSON） =========================

    /**
     * 将对象序列化为 JSON 后写入。
     */
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
    }

    /**
     * 将对象序列化为 JSON 后写入，并设置过期时间。
     */
    public void setObject(String key, Object value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(value), time, unit);
    }

    /**
     * 读取 JSON 并反序列化为指定类型；key 不存在或内容为空时返回 {@code null}。
     */
    public <T> T getObject(String key, Class<T> clazz) {
        String json = redisTemplate.opsForValue().get(key);
        return StringUtils.hasText(json) ? JSON.parseObject(json, clazz) : null;
    }

    // ========================= Hash（存对象字段） =========================

    /**
     * 写入 Hash 字段。非 {@link String} 值会转为 JSON 字符串，与 {@link StringRedisTemplate} 保持一致。
     */
    public void hset(String key, String field, Object value) {
        String stored = value instanceof String ? (String) value : JSON.toJSONString(value);
        redisTemplate.opsForHash().put(key, field, stored);
    }

    /**
     * 读取 Hash 单个字段。
     */
    public String hget(String key, String field) {
        Object value = redisTemplate.opsForHash().get(key, field);
        return value == null ? null : value.toString();
    }

    /**
     * 读取 Hash 字段并反序列化为对象。
     */
    public <T> T hgetObject(String key, String field, Class<T> clazz) {
        String json = hget(key, field);
        return StringUtils.hasText(json) ? JSON.parseObject(json, clazz) : null;
    }

    /**
     * 读取 Hash 全部字段。
     */
    public Map<Object, Object> hgetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除 Hash 中的一个或多个字段。
     */
    public Long hdel(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    // ========================= List（队列） =========================

    /**
     * 从列表左侧入队（常用于消息队列、最新动态）。
     */
    public void lpush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 从列表右侧出队；列表为空时返回 {@code null}。
     */
    public String rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 按索引区间读取列表，{@code start}/{@code end} 含义与 Redis LRANGE 一致（{@code 0} 为第一个元素）。
     */
    public List<String> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // ========================= Set（去重） =========================

    /**
     * 向集合添加成员。
     */
    public void sadd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 返回集合全部成员。
     */
    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断成员是否在集合中。
     */
    public Boolean sismember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    // ========================= ZSet（排行榜） =========================

    /**
     * 向有序集合添加成员及分数。
     */
    public void zadd(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 按分数从低到高返回指定排名区间的成员。
     */
    public Set<String> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 按分数从高到低返回指定排名区间的成员（排行榜常用）。
     */
    public Set<String> zrevrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    // ========================= Common =========================

    /**
     * 删除 key。
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断 key 是否存在。
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 为已存在的 key 设置过期时间。
     */
    public Boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.expire(key, time, unit);
    }

    /**
     * 获取 key 剩余过期时间（秒）；key 不存在返回 {@code -2}，永不过期返回 {@code -1}。
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
