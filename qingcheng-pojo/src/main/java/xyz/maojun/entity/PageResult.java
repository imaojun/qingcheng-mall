package xyz.maojun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Description: Pageresult
 * Created by maojun
 * Date: 2019/08/04
 */
@Getter
@Setter
@AllArgsConstructor
public class PageResult<T>  implements Serializable {
    private  long total;
    private List<T> rows;

}
