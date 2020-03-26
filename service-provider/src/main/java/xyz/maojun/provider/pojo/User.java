package xyz.maojun.provider.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private String id;
    @TableField("name")
    private String username;
    private String sex;
    private int age;
}
