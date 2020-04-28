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
@RequiredArgsConstructor
public class PageResult<T> {
    @NonNull
    private Long total;
    private Integer totalPage;
    @NonNull
    private List<T> items;

}
