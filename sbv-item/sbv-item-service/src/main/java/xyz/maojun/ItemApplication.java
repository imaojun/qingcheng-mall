package xyz.maojun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:item applicatioin
 * @Created by maojun
 * @Date: 2020/04/24
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("xyz.maojun.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
