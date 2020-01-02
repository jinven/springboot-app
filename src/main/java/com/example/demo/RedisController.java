package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class RedisController {
	
	@RequestMapping("/redis")
	public String index() {
        String message = "";
        try {
            Jedis jedis = new Jedis("localhost", 6379);
            message += "ping: " + jedis.ping();
            message += "<br>";
            jedis.set("foo", "bar");
            message += "foo: " + jedis.get("foo");
            message += "<br>";
            message += "redis连接测试成功";
            jedis.close();
        } catch (Exception e) {
            message += "redis连接失败";
            e.printStackTrace();
        }
		return message;
	}
}
