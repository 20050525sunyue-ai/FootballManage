package com.sky.base.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;

/**
 * 阿里云对象存储（OSS）上传工具类。
 * <p>
 * 封装了基于官方 SDK 的 {@code putObject} 上传逻辑，将字节流写入指定 Bucket 中的对象键（ObjectName），
 * 并拼接返回可在浏览器中直接访问的 HTTPS 公网 URL（适用于默认 Bucket 为公共读或已配置 CDN/访问策略的场景）。
 * </p>
 * <p>
 * 实例通常由 Spring 容器创建：在 {@code OssConfiguration} 中读取 {@code AliOssProperties}（endpoint、密钥、Bucket 等）
 * 后注入到 Controller、Service 中使用。
 * </p>
 */
@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    /** OSS 访问域名，不含协议，例如 {@code oss-cn-hangzhou.aliyuncs.com}。需与 Bucket 所在地域一致。 */
    private String endpoint;
    /** RAM 用户或子账号的 AccessKey ID，用于身份认证。切勿硬编码提交到版本库，应走配置文件或环境变量。 */
    private String accessKeyId;
    /** 与 AccessKey ID 配对的 Secret，权限等同于该 RAM 账号在 OSS 上的授权范围。 */
    private String accessKeySecret;
    /** 存储空间名称，上传文件会落在该 Bucket 下；需保证 RAM 账号对该 Bucket 有 {@code PutObject} 等写权限。 */
    private String bucketName;

    /**
     * 将内存中的文件字节上传到当前配置的 Bucket，并返回拼接后的访问地址。
     * <p>
     * 流程说明：
     * <ol>
     *   <li>使用 endpoint、AccessKey 构建 {@link OSS} 客户端（短生命周期，用完在 {@code finally} 中关闭）。</li>
     *   <li>调用 {@code putObject(bucketName, objectName, inputStream)}，其中流由 {@code bytes} 包装而来。</li>
     *   <li>无论上传是否成功，都会在 {@code finally} 中执行 {@code shutdown()}，避免连接泄漏。</li>
     *   <li>方法末尾按「虚拟主机风格」拼接 URL：{@code https://{bucketName}.{endpoint}/{objectName}}。</li>
     * </ol>
     * </p>
     * <p>
     * 异常行为：若发生 {@link OSSException}（服务端拒绝、权限不足、Bucket 不存在等）或
     * {@link ClientException}（网络不可达、签名错误等），当前实现仅向标准输出打印诊断信息，
     * <strong>不会向上抛出</strong>，调用方仍会得到拼接后的 URL 字符串；若需严格校验上传结果，应在业务层根据返回值或改造为抛异常/返回 {@code Optional}。
     * </p>
     *
     * @param bytes      文件的完整字节内容，不可为 {@code null}；大文件建议考虑分片上传（本方法为简单一次性上传）。
     * @param objectName OSS 中的对象键，可包含「目录」前缀，例如 {@code images/2024/avatar.png}；
     *                   同一键重复上传会覆盖原有对象。
     * @return 拼接得到的 HTTPS 访问 URL，形如 {@code https://your-bucket.oss-cn-xxx.aliyuncs.com/path/to/file.ext}
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("文件上传到:{}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}
