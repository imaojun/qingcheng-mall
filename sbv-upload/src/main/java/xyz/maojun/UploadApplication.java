package xyz.maojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: Upload Application
 * @Created by maojun
 * @Date: 2020/04/28
 */

@SpringBootApplication
@EnableDiscoveryClient
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
