package com.leyou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 设置上传属性，属性在yml中配置，此类进行读取属性
 */
@Data
@ConfigurationProperties(prefix = "ly.upload") //读取yml中配置属性
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
