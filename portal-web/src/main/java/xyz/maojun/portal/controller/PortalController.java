package xyz.maojun.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.content.service.ContentService;
import xyz.maojun.pojo.TbContent;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PortalController {
    @Value("${CONTENT_LIST_ID}")
    private Long CONTENT_ID;
    @Resource
    private ContentService contentService;
   @RequestMapping("/index")
   public String showPortal(Model model){
       List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_ID);
       model.addAttribute("ad1List", ad1List);
       return "index";
   }

}
