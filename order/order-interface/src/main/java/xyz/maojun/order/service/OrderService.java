package xyz.maojun.order.service;

import xyz.maojun.common.util.EgouResult;
import xyz.maojun.order.pojo.OrderInfo;

public interface OrderService {
    EgouResult createOrder(OrderInfo orderInfo);
}
