package xyz.maojun.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.maojun.pojo.Category;
import xyz.maojun.item.service.CategoryService;

import java.util.List;

/**
 * @Description: Category Controller
 * @Created by maojun
 * @Date: 2020/04/27
 */

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * query parent id find child catetory list
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> getCategoryListById(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        if (pid == null || pid < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Category> categoryList = categoryService.getCategoryListById(pid);

        if (CollectionUtils.isEmpty(categoryList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(categoryList);

    }



}
