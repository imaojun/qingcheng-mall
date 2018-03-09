package xyz.maojun.cart.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.omg.CORBA.IRObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.cart.service.CartService;
import xyz.maojun.common.util.CookieUtils;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.SplitPaneUI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Value("${CART_COOKIE_EXPIRE}")
    private Integer CART_COOKIE_EXPIRE;
    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
                          HttpServletRequest request, HttpServletResponse response) {
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.addCart(user.getId(), itemId, num);
            return "cartSuccess";
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        boolean flag = false;
        for (TbItem tbItem : cartList) {
            if (tbItem.getId() == itemId.longValue()) {
                flag = true;
                tbItem.setNum(tbItem.getNum() + num);
                break;
            }
        }
        if (!flag) {
            TbItem tbItem = itemService.getItemById(itemId);
            tbItem.setNum(num);
            String image = tbItem.getImage();
            if (StringUtils.isNotBlank(image)) {
                tbItem.setImage(image.split(",")[0]);
            }
            cartList.add(tbItem);
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CART_COOKIE_EXPIRE, true);

        return "cartSuccess";
    }

    private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)) {
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }

    @RequestMapping("/cart/cart")
    public String showCatList(HttpServletRequest request,HttpServletResponse response) {
        List<TbItem> cartList = getCartListFromCookie(request);
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.mergeCart(user.getId(), cartList);
            CookieUtils.deleteCookie(request,response,"cart");
            cartList = cartService.getCartList(user.getId());
        }
        request.setAttribute("cartList", cartList);
        return "cart";
    }

    @RequestMapping("/cart/update/num{itemId}/{num}")
    @ResponseBody
    public EgouResult updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
                                    HttpServletRequest request, HttpServletResponse response) {
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.updateCarNum(user.getId(), itemId, num);
            return EgouResult.ok();
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        for (TbItem tbItem : cartList) {
            if (tbItem.getId().equals(itemId)) {
                tbItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CART_COOKIE_EXPIRE, true);
        return EgouResult.ok();
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.deleteCartItem(user.getId(), itemId);
            return "redirect:/cart/cart.html";
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        for (TbItem tbItem : cartList) {
            if (tbItem.getId().equals(itemId)) {
                cartList.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), CART_COOKIE_EXPIRE, true);
        return "redirect:/cart/cart.html";
    }

}
