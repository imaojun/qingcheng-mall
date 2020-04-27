package xyz.maojun.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.maojun.item.service.BrandService;
import xyz.maojun.pojo.Brand;
import xyz.maojun.pojo.PageResult;

/**
 * @Description: Brand Controller
 * @Created by maojun
 * @Date: 2020/04/27
 */

@RequestMapping("brand")
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(@RequestParam(value = "key", required = false) String key,
                                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                               @RequestParam(value = "soryBy", required = false) String soryBy,
                                                               @RequestParam(value = "desc", required = false) Boolean desc
    ) {

        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, soryBy, desc);

        if (result == null || CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

}
