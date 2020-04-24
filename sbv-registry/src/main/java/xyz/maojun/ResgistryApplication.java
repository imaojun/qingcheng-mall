package xyz.maojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: register application
 * @Created by maojun
 * @Date: 2020/04/24
 */

@SpringBootApplication
@EnableEurekaServer
public class ResgistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResgistryApplication.class, args);
    }
}
