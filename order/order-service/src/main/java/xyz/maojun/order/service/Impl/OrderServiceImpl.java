package xyz.maojun.order.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.mapper.TbOrderItemMapper;
import xyz.maojun.mapper.TbOrderMapper;
import xyz.maojun.mapper.TbOrderShippingMapper;
import xyz.maojun.order.pojo.OrderInfo;
import xyz.maojun.pojo.TbOrderItem;
import xyz.maojun.pojo.TbOrderShipping;
import xyz.maojun.order.service.OrderService;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;
    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;
    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN;
    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private JedisClient jedisClient;
    @Override
    public EgouResult createOrder(OrderInfo orderInfo) {
        if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
            jedisClient.set(ORDER_ID_GEN_KEY,ORDER_ID_START);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
        orderInfo.setOrderId(orderId);
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderMapper.insert(orderInfo);
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            String odId = jedisClient.incr(ORDER_DETAIL_ID_GEN).toString();
            orderItem.setId(odId);
            orderItem.setOrderId(orderId);
            tbOrderItemMapper.insert(orderItem);
        }
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        tbOrderShippingMapper.insert(orderShipping);
        return EgouResult.ok(orderId);
    }
}
