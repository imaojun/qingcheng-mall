package xyz.maojun.sso.controller;

import javafx.scene.control.TabBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.sso.service.RegisterService;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegister() {
        return "register";
    }


    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public EgouResult checkData(@PathVariable String param, @PathVariable Integer type) {
        EgouResult egouResult = registerService.checkDate(param, type);
        return egouResult;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult register(TbUser user) {
        EgouResult egouResult = registerService.register(user);
        return egouResult;
    }

}
