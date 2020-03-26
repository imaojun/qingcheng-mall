package xyz.maojun.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.provider.mapple.UserMapple;
import xyz.maojun.provider.pojo.User;
import xyz.maojun.provider.service.UserService;

/**
 * @Description: user impl
 * @Created by maojun
 * @Date: 2020/03/26
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserMapple userMapple;

    @Override
    public User selectUserById(String id) {
        User user = userMapple.selectById(id);
        return user;
    }
}
