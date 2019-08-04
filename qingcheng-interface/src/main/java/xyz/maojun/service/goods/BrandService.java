package xyz.maojun.service.goods;

import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> findAll();

    PageResult<Brand> findPage(int page, int size);
}
