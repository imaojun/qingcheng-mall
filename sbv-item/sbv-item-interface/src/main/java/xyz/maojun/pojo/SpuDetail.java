package xyz.maojun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: Spu detail
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Getter
@Setter
@ToString
@TableName("tb_spu_detail")
public class SpuDetail {

    @TableId(type = IdType.AUTO)
    private Long spuId;
    private String description;
    private String specTemplate;
    private String specifications;
    private String packingList;
    private String afterService;
}
