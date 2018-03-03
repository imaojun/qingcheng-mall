package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.pojo.SearchItem;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.search.service.SearchItemService;

import javax.annotation.Resource;

@Controller
public class SearchItemController {
    @Resource
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public EgouResult importItemList(){
        EgouResult egouResult = searchItemService.importALLItem();
        return egouResult;
    }

}
