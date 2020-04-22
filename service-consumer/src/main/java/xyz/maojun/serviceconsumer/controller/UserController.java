package xyz.maojun.serviceconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.serviceconsumer.client.UserClient;

/**
 * @Description: user controller
 * @Created by maojun
 * @Date: 2020/03/26
 */


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClient userClient;


    @GetMapping("{id}")
    @ResponseBody
    @HystrixCommand
    public String getUserById(@PathVariable("id") String id) {

        return userClient.getUserById(id).toString();

    }




}
