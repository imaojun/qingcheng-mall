package xyz.maojun.sso.service;

import xyz.maojun.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
