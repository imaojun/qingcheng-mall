package xyz.maojun.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.maojun.provider.pojo.User;
import xyz.maojun.provider.service.UserService;

/**
 * @Description: user controller
 * @Created by maojun
 * @Date: 2020/03/26
 */

@RestController
@RequestMapping("/getUser")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
      return   userService.selectUserById(id);
    }

}
