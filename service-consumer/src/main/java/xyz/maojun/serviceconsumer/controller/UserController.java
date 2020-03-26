package xyz.maojun.serviceconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import xyz.maojun.serviceconsumer.pojo.User;

import java.util.List;

/**
 * @Description: user controller
 * @Created by maojun
 * @Date: 2020/03/26
 */


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") String id){
        List<ServiceInstance> instances = discoveryClient.getInstances("SERVICE-PROVIDER");
        ServiceInstance ins = instances.get(0);
        User user = restTemplate.getForObject("http://"+ins.getHost()+":"+ins.getPort() +"/user/"+id, User.class);
        return user;

    }





}
