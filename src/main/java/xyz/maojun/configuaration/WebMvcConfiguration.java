package xyz.maojun.configuaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.maojun.intercepter.Myintercepter;

/**
 * @Description: webmvc configuration
 * @Created by maojun
 * @Date: 2020/03/25
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
   private Myintercepter myintercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myintercepter).addPathPatterns("/**");

    }
}
