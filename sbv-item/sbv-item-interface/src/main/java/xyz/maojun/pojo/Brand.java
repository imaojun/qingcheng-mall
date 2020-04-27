package xyz.maojun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: Brand
 * @Created by maojun
 * @Date: 2020/04/27
 */

@TableName("tb_brand")
@Getter
@Setter
@ToString
public class Brand {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String image;
    private Character letter;
}
