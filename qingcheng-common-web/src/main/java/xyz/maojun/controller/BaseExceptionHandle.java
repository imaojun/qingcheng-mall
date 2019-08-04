package xyz.maojun.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.maojun.entity.Result;

/**
 * Description: global catch exception
 * Created by maojun
 * Date: 2019/08/04
 */
@ControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        System.out.println("系统抛出了异常"+e);
        return new Result(1, e.getMessage());
    }

}
