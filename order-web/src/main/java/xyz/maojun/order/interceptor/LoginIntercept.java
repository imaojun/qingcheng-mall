package xyz.maojun.order.interceptor;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.maojun.cart.service.CartService;
import xyz.maojun.common.util.CookieUtils;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbUser;
import xyz.maojun.sso.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercept implements HandlerInterceptor{

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, "token");
        if (StringUtils.isBlank(token)) {
            httpServletResponse.sendRedirect(SSO_URL + "/page/login?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        EgouResult egouResult = tokenService.getUserByToken(token);
        if (egouResult.getStatus() != 200) {
            httpServletResponse.sendRedirect(SSO_URL + "/page/login?redirect=" + httpServletRequest.getRequestURL());
            return false;
        }
        TbUser user = (TbUser) egouResult.getData();
        httpServletRequest.setAttribute("user", user);
        String cart = CookieUtils.getCookieValue(httpServletRequest, "cart", true);
        if (StringUtils.isNotBlank(cart)) {
            cartService.mergeCart(user.getId(), JsonUtils.jsonToList(cart, TbItem.class));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
