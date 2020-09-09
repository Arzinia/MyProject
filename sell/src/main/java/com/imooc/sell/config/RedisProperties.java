package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.cache")
public class RedisProperties {
    private Integer expireSeconds;
    private String clusterNodes;
    private Integer commandTimeout;
    Object object = new Object();
}
