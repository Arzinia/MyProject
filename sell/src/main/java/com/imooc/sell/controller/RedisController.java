package com.imooc.sell.controller;

import com.imooc.sell.config.JedisClusterConfig;
import com.imooc.sell.service.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RedisController {
    @Autowired
    private RedisClientTemplate redisClientTemplate;
    @GetMapping(value = "/testSet")
    public void testSet(){
        redisClientTemplate.setToRedis("Frank","Frank测试redis");
        System.out.println(redisClientTemplate.getRedis("Frank"));
    }
}
