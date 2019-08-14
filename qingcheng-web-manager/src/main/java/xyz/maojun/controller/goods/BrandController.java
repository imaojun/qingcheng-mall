package xyz.maojun.controller.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import xyz.maojun.entity.PageResult;
import xyz.maojun.entity.Result;
import xyz.maojun.pojo.goods.Brand;
import xyz.maojun.service.goods.BrandService;

import java.util.List;
import java.util.Map;

/**
 * Description: BrandController
 * Created by maojun
 * Date: 2019/08/04
 * Time: 下午2:58
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @GetMapping("/findAll")
    public List<Brand> findALl() {
        return brandService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String, String> map) {
        return brandService.findList(map);
    }

    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String, String> map, int page, int size) {
        return brandService.findPage(map, page, size);
    }


    @GetMapping("/findById")
    public Brand findById(int id) {
        return brandService.findById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Brand brand) {
        brandService.update(brand);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(int id) {
        brandService.delete(id);
        return new Result();
    }
}
