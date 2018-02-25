package xyz.maojun.runspring;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class RunSpring {
    @Test
    public void runSpring() throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        System.out.println("启动spring容器----用来替代tomcata初始化spring容器");
        System.in.read();
        System.out.println("服务停止");

    }
}
