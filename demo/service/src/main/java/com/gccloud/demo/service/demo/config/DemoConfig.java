package com.gccloud.demo.service.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通过yaml配置，然后映射到该对象中
 *
 * @author qianxing
 * @version 1.0
 * @date 2021/5/18 9:37
 */
@Configuration
@ConfigurationProperties(prefix = "demo")
@Data
public class DemoConfig {
    private Boolean enable = Boolean.TRUE;
    private String url;
    private List<String> list;
}
