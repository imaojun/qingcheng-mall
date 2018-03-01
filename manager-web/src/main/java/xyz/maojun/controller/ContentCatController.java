package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.common.pojo.EasyUITreeNode;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.content.service.ContentCategoryService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ContentCatController {

   @Autowired
   private ContentCategoryService contentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentID) {
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentID);
        return contentCatList;

    }

    @RequestMapping(value = "/content/category/list",method = RequestMethod.POST)
    @ResponseBody
    public EgouResult createContent(Long parentId,String name){
        EgouResult egouResult = contentCategoryService.addContentCategory(parentId, name);
        return egouResult;
    }


}
