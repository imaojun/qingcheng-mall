package xyz.maojun.serviceconsumer.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: User
 * @Created by maojun
 * @Date: 2020/03/25
 */

@Getter
@Setter
@ToString
public class User {

    private String id;
    private String username;
    private String sex;
    private int age;
}
