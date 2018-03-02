package xyz.maojun.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import xyz.maojun.common.jedis.JedisClient;

public class JedisClientTest {
    @Test
    public void testJedisClient(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisClient jedis = applicationContext.getBean(JedisClient.class);
        jedis.set("test2", "test2");
        String test2 = jedis.get("test2");
        System.out.println(test2);
    }

    @Test
    public void testJedis(){
        JedisPool jedisPool = new JedisPool("172.20.10.15", 6379);
        Jedis resource = jedisPool.getResource();
        resource.set("hello222", "ewi");
        String hello222 = resource.get("hello222");
        System.out.println(hello222);
        resource.close();
        jedisPool.close();
    }
}
