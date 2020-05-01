package xyz.maojun.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.maojun.bo.SpuBo;
import xyz.maojun.item.service.GoodsService;
import xyz.maojun.pojo.PageResult;

/**
 * @Description: goods controller
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Controller
public class GoodsController {


    @Autowired
    private GoodsService goodsService;


    /**
     * query spu by page
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(@RequestParam(value = "key", required = false) String key,
                                                            @RequestParam(value = "saleable", required = false) Boolean saleable,
                                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "5") Integer rows) {

     PageResult<SpuBo>  result= this.goodsService.querySpuByPage(key, saleable, page, rows);

        if (result == null || CollectionUtils.isEmpty(result.getItems())) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
