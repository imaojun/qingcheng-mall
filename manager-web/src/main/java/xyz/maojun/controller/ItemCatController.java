package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.pojo.EasyUITreeNode;
import xyz.maojun.service.ItemCatService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId) {
        List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);

        return itemCatList;
    }
}
