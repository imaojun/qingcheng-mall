package xyz.maojun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: Category
 * @Created by maojun
 * @Date: 2020/04/27
 */

@TableName("tb_category")
@Getter
@Setter
@ToString
public class Category {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long parentId;
    @TableField("is_parent")
    private Boolean parent;
    private Integer sort;



}
