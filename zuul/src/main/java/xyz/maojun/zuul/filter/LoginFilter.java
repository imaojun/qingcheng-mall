package xyz.maojun.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Created by maojun
 * @Date: 2020/04/22
 */

@Component
public class LoginFilter extends ZuulFilter {

    /**
     * filter type [pre route post error]
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * The lower the number, the higher the priority level
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     *  true exec run()  method
     *  false not exec run() method
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();

        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            // Do not forward the request
            context.setSendZuulResponse(false);
            // Set status code and msg
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            context.getResponse().setCharacterEncoding("UTF-8");
            context.getResponse().setContentType("text/html;cahrset=UTF-8");
            context.setResponseBody("requset error,未登入用户无法访问");
        }

        return null;
    }
}
