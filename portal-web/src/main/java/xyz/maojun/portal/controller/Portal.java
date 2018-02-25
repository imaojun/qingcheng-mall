package xyz.maojun.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Portal {
   @RequestMapping("/index")
   public String showPortal(){
      return "index";
   }

}
