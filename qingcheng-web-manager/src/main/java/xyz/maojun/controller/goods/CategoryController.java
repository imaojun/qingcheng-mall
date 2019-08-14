package xyz.maojun.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.maojun.entity.PageResult;
import xyz.maojun.entity.Result;
import xyz.maojun.pojo.goods.Category;
import xyz.maojun.service.goods.CategoryService;

import java.util.List;
import java.util.Map;

/**
 * Description: CategoryController
 * Created by maojun
 * Date: 2019/08/14
 * Time: 9:52 PM
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Category> findPage(int page, int size) {
        return categoryService.findPage(page, size);
    }


    @PostMapping("/findList")
    public List<Category> findList(Map<String, Object> searchMap) {
        return categoryService.findList(searchMap);
    }


    @PostMapping("/findPage")
    public PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size) {
        return categoryService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public Category findById(Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping("/add")
    public Result add(Category category) {
        categoryService.add(category);
        return new Result();
    }


    @PostMapping("/update")
    public Result update(Category category) {
        categoryService.update(category);
        return new Result();
    }


    @GetMapping("/delete")
    public Result delete(Integer id) {
        categoryService.delete(id);
        return new Result();
    }
}
