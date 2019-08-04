package xyz.maojun.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Brand;
import xyz.maojun.service.goods.BrandService;

import java.util.List;

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

    @RequestMapping("/findAll")
    public List<Brand> findALl() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page, size);
    }
}
