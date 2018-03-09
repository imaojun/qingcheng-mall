package xyz.maojun.cart.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.maojun.cart.service.CartService;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.mapper.TbItemMapper;
import xyz.maojun.pojo.TbItem;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private TbItemMapper itemMapper;

    @Value("$(REDIS_CART_PRE)")
    private String REDIS_CART_PRE;

    @Override
    public EgouResult addCart(long usrId, long itemId,int num) {
        Boolean hexists = jedisClient.hexists(REDIS_CART_PRE + ":" + usrId, itemId + "");
        if (hexists) {
            String json = jedisClient.hget(REDIS_CART_PRE + ":" + usrId, itemId + "");
            TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
            tbItem.setNum(tbItem.getNum() + num);
            jedisClient.hset(REDIS_CART_PRE + ":" + usrId, itemId + "", JsonUtils.objectToJson(tbItem));
            return EgouResult.ok();
        }
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        item.setNum(num);
        String image = item.getImage();
        if (StringUtils.isNotBlank(image)) {
            item.setImage(image.split(",")[0]);
        }
        //添加到购物车列表
        jedisClient.hset(REDIS_CART_PRE + ":" + usrId, itemId + "", JsonUtils.objectToJson(item));
        return EgouResult.ok();
    }

    @Override
    public EgouResult mergeCart(long userId, List<TbItem> itemList) {
        for (TbItem tbItem : itemList) {
            addCart(userId, tbItem.getId(), tbItem.getNum());
        }
        return EgouResult.ok();
    }

    @Override
    public List<TbItem> getCartList(long userId) {
        List<String> jsonList = jedisClient.hvals(REDIS_CART_PRE + ":" + userId);
        List<TbItem> itemlist = new ArrayList<>();
        for (String string : jsonList) {
            TbItem item = JsonUtils.jsonToPojo(string, TbItem.class);
            itemlist.add(item);
        }
        return itemlist;
    }

    @Override
    public EgouResult updateCarNum(long userId, long itemId, int num) {
        String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
        TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
        tbItem.setNum(num);
        jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(tbItem));
        return EgouResult.ok();
    }

    @Override
    public EgouResult deleteCartItem(long userId, long itemId) {
        jedisClient.hdel(REDIS_CART_PRE + ":" + userId, itemId + "");
        return EgouResult.ok();
    }

    @Override
    public EgouResult clearCartItem(long userId) {
        jedisClient.del(REDIS_CART_PRE + ":" + userId);
        return EgouResult.ok();
    }

}
