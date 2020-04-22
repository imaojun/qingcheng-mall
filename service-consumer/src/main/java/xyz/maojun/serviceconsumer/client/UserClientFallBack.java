package xyz.maojun.serviceconsumer.client;

import org.springframework.stereotype.Component;
import xyz.maojun.serviceconsumer.pojo.User;

/**
 * @Description:
 * @Created by maojun
 * @Date: 2020/04/22
 */

@Component
public class UserClientFallBack  implements  UserClient{

    @Override
    public User getUserById(String id) {
        User user = new User();
        user.setUsername("服务正忙，请稍后再试");
        return user;
    }
}
