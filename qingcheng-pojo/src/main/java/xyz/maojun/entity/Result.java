package xyz.maojun.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: Result
 * Created by maojun
 * Date: 2019/08/04
 */
@Getter
@Setter
@AllArgsConstructor
public class Result {
    private int code;
    private String message;

    public Result() {
        this.code=0;
        this.message = "success";
    }
}
