package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.content.service.ContentService;
import xyz.maojun.pojo.TbContent;

import javax.annotation.Resource;

@Controller
public class ContentController {
    @Resource
    private ContentService contentService;

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public EgouResult addContent(TbContent content) {
        EgouResult result = contentService.addContent(content);
        return result;
    }
}
