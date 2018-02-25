package xyz.maojun.service;

import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(long itemid);
    EasyUIDateGridResult getItemList(int page,int rows);
    EgouResult addItem(TbItem item, String desc);
}
