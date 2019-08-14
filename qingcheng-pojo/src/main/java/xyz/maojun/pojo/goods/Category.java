package xyz.maojun.pojo.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description: Category
 * Created by maojun
 * Date: 2019/08/14
 * Time: 9:02 PM
 */
@Table(name="tb_category")
@Getter
@Setter
@ToString
public class Category implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Integer goodsNum;
    private String isShow;
    private String isMenu;
    private Integer seq;
    private Integer parentId;
    private Integer templateId;
}
