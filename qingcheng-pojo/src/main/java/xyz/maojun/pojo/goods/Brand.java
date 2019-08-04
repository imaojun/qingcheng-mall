package xyz.maojun.pojo.goods;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description: Brand
 * Created by maojun
 * Date: 2019/08/04
 */
@Table(name = "tb_brand")
@Data
public class Brand implements Serializable {
    @Id
    private Integer id;

    private String name;

    private String image;

    private String letter;

    private Integer seq;
}


