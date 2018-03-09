package xyz.maojun.cart.service;

import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbItem;

import java.util.List;

public interface CartService {
    EgouResult addCart(long usrId, long itemId,int num);

    EgouResult mergeCart(long userId, List<TbItem> itemList);

    List<TbItem> getCartList(long userId);

    EgouResult updateCarNum(long userId, long itemId, int num);

    EgouResult deleteCartItem(long userId, long itemId);

    EgouResult clearCartItem(long userId);

}
