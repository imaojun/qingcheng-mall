package xyz.maojun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IREM;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.jedis.JedisClientPool;
import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.IDUtils;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.mapper.TbItemDescMapper;
import xyz.maojun.mapper.TbItemMapper;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemDesc;
import xyz.maojun.pojo.TbItemExample;
import xyz.maojun.service.ItemService;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource
    private Destination topicDestination;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_PRE}")
    private String REDIS_ITEM_PRE;

    @Value(("${ITEM_CACHE_EXPIRE}"))
    private Integer ITEM_CACHE_EXPIRE;

    @Override
    public TbItem getItemById(long itemid) {
        // 查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemid + ":BASE");
            if (StringUtils.isNotBlank(json)) {
                TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
                return tbItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据主键查询
//        TbItem item = tbItemMapper.selectByPrimaryKey(itemid);
//        return item;
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andIdEqualTo(itemid);
        // 执行查询
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            // 添加到缓存
            try {
                jedisClient.set(REDIS_ITEM_PRE+":" + itemid + ":BASE", JsonUtils.objectToJson(list.get(0)));
                jedisClient.expire(REDIS_ITEM_PRE+":" + itemid + ":BASE", ITEM_CACHE_EXPIRE);
            } catch (Exception e) {
               e.printStackTrace();
            }
            return list.get(0);
        }
        return null;
    }

    @Override
    public EasyUIDateGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);

        EasyUIDateGridResult result = new EasyUIDateGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public EgouResult addItem(TbItem item, String desc) {
        // 生成商品id
        final long itemId = IDUtils.genItemId();
        //补全item属性
        item.setId(itemId);
        //商品状态 1正常 2下架 3删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        // 向商品表中插入数据
        tbItemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                return textMessage;
            }
        });
        return EgouResult.ok();
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        // 查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
            if (StringUtils.isNotBlank(json)) {
                TbItemDesc tbItemDesc= JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return tbItemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(REDIS_ITEM_PRE+":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }
}
