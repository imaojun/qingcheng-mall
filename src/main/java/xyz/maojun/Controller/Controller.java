package xyz.maojun.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.pojo.User;
import xyz.maojun.service.UserService;

import java.util.List;

/**
 * @Description: controller
 * @Created by maojun
 * @Date: 2020/03/25
 */

@org.springframework.stereotype.Controller
@RequestMapping("/hello")
public class Controller {


    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public String hello() {
        return "hello spring boot";
    }


    @GetMapping("save")
    @ResponseBody
    public String saveUser() {

        int i = userService.saveUser();
        if (i == 1) {
            return "success";
        }

        return "fail";

    }

    @GetMapping("list")
    public String queryAll(Model model){
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        List<User> users = userService.queryAll(userWrapper);
        model.addAttribute("users", users);
        return "user";
    }


}
