package xyz.maojun.serviceconsumer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: resttemptconfig
 * @Created by maojun
 * @Date: 2020/03/26
 */


@Configuration
public class RestTempConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
