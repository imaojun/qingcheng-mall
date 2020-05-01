package xyz.maojun.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.maojun.pojo.Spu;

/**
 * @Description: spu bo
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Getter
@Setter
@ToString
public class SpuBo extends Spu {
    private String cname;
    private String bname;
}
