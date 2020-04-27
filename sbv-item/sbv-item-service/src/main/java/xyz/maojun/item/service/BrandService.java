package xyz.maojun.item.service;

import xyz.maojun.pojo.Brand;
import xyz.maojun.pojo.PageResult;

public interface BrandService {

    PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String soryBy, Boolean desc);
}
