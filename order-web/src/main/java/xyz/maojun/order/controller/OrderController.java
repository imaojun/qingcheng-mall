package xyz.maojun.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.maojun.cart.service.CartService;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.order.pojo.OrderInfo;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.order.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request) {
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList", cartList);
        return "order-cart";
    }
    @RequestMapping(value="/order/create", method= RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, HttpServletRequest request) {
        //取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        //把用户信息添加到orderInfo中。
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务生成订单
        EgouResult egouResult = orderService.createOrder(orderInfo);
        //如果订单生成成功，需要删除购物车
        if (egouResult.getStatus() == 200) {
            //清空购物车
            cartService.clearCartItem(user.getId());
        }
        //把订单号传递给页面
        request.setAttribute("orderId", egouResult.getData());
        request.setAttribute("payment", orderInfo.getPayment());
        //返回逻辑视图
        return "success";
    }
}
