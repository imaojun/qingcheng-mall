package xyz.maojun.item.service;

import xyz.maojun.pojo.Brand;
import xyz.maojun.pojo.PageResult;

import java.util.List;

public interface BrandService {

    PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String soryBy, Boolean desc);

    void saveBrand(Brand brand, List<Long> cids);
}
