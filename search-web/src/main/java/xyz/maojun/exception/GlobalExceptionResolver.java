package xyz.maojun.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalExceptionResolver implements HandlerExceptionResolver{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        LOGGER.error("system Exception",e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }

}
