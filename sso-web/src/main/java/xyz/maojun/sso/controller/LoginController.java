package xyz.maojun.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.util.CookieUtils;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.sso.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public EgouResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        EgouResult egouResult = loginService.userLogin(username, password);
        if (egouResult.getStatus() == 200) {
            String token = egouResult.getData().toString();
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        return egouResult;
    }
}
