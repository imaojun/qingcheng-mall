package xyz.maojun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Description: spec group
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Getter
@Setter
@ToString
@TableName("tb_spec_group")
public class SpecGroup {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long cid;
    private String name;
    @TableField(exist = false)
    private List<SpecParam> params;



}
