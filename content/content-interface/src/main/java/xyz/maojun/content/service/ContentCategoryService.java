package xyz.maojun.content.service;

import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.pojo.EasyUITreeNode;
import xyz.maojun.common.util.EgouResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentID);
    EgouResult addContentCategory(long parentID,String name);
}
