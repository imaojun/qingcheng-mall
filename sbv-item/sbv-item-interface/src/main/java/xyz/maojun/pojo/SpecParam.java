package xyz.maojun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: spec params
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Getter
@Setter
@ToString
@TableName("tb_spec_param")
public class SpecParam {
    @TableId(type= IdType.AUTO)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @TableField("'numeric'")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
