package org.ifunq.tanzx.spring.DataRedis;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

public class SpringDataRedisTest01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/DATA/Spring-Data-Redis.xml");
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("XXX", "tanzongxi");
        System.out.println(redisTemplate.opsForValue().get("XXX"));
        redisTemplate.delete("XXX");
        Set<String> keys = redisTemplate.keys("warehouse_*");
        System.out.println(keys);
    }
}
