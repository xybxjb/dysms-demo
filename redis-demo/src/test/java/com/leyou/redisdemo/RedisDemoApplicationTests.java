package com.leyou.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        stringRedisTemplate.opsForValue().set("test", "hello world");
        String test = stringRedisTemplate.opsForValue().get("tests");
        System.out.println("test = " + test);

        BoundHashOperations<String, Object, Object> ops = stringRedisTemplate.boundHashOps("user:123");
        ops.put("name", "rose");
        ops.put("age", "21");

        ops.get("name");

        Map<Object, Object> map = ops.entries();
        System.out.println("entries = " + map);
    }

}
