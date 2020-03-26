package xyz.maojun.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import xyz.maojun.pojo.User;

import java.util.List;

/**
 * @Description: User service
 * @Created by maojun
 * @Date: 2020/03/26
 */

public interface UserService {


    int saveUser();

    List<User>  queryAll(QueryWrapper<User> wrapper);
}
