package xyz.maojun.provider.service;

import xyz.maojun.provider.pojo.User;

/**
 * @Description: user service
 * @Created by maojun
 * @Date: 2020/03/26
 */
public interface UserService {


  User selectUserById(String id);
}
