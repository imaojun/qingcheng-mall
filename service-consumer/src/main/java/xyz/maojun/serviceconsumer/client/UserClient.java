package xyz.maojun.serviceconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.maojun.serviceconsumer.pojo.User;

/**
 * @Description:
 * @Created by maojun
 * @Date: 2020/04/22
 */

@FeignClient(value = "service-provider",fallback = UserClientFallBack.class)
@Primary
public interface UserClient {

    @GetMapping("getUser/{id}")
    public User getUserById(@PathVariable("id") String id);
}
