package xyz.maojun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.maojun.mapple.UserMapper;
import xyz.maojun.pojo.User;
import xyz.maojun.service.UserService;

import java.util.List;

/**
 * @Description: user impl
 * @Created by maojun
 * @Date: 2020/03/26
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public int saveUser() {
        User user = new User();
        user.setAge(22);
        user.setId("2");
        user.setSex("girl");
        user.setUsername("李四");
        return userMapper.insert(user);
    }

    @Override
    public List<User> queryAll(QueryWrapper<User> wrapper) {
        return userMapper.selectList(wrapper);
    }
}
