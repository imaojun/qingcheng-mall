package xyz.maojun.pojo;

import lombok.*;

import java.util.List;

/**
 * @Description: Page Result
 * @Created by maojun
 * @Date: 2020/04/27
 */

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;


    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
