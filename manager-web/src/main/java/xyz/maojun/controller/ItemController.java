package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.pojo.*;
import xyz.maojun.service.ItemService;

import javax.annotation.Resource;

@Controller
public class ItemController {

    @Resource
    private ItemService itemService;


    @RequestMapping("/list/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;

    }


}
