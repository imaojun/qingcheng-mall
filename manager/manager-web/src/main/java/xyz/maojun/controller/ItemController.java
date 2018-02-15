package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.service.ItemService;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/list/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;

    }

}
