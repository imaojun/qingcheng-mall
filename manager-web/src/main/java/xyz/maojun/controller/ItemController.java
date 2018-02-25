package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.*;
import xyz.maojun.service.ItemService;

import javax.annotation.Resource;

@Controller
public class ItemController {

    @Resource
    private ItemService itemService;


    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDateGridResult getItemList(Integer page, int rows){
        EasyUIDateGridResult result =itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value = "item/save",method = RequestMethod.POST)
    @ResponseBody
    public EgouResult addItem(TbItem item, String desc) {
        EgouResult result = itemService.addItem(item, desc);
        return result;
    }


}
