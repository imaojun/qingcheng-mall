package xyz.maojun.sso.service;

import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemDesc;

public interface ItemService {
    TbItem getItemById(long itemid);
    EasyUIDateGridResult getItemList(int page,int rows);
    EgouResult addItem(TbItem item, String desc);
    TbItemDesc getItemDescById(long itemId);

}
