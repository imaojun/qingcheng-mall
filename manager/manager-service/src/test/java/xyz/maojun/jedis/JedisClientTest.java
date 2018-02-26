package xyz.maojun.jedis;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
}
