package xyz.maojun.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.item.mapper.BrandMapper;
import xyz.maojun.item.service.BrandService;
import xyz.maojun.pojo.Brand;
import xyz.maojun.pojo.PageResult;

/**
 * @Description:
 * @Created by maojun
 * @Date: 2020/04/27
 */

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String soryBy, Boolean desc) {
        Brand brand = new Brand();

        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();

        Page<Brand> pageBrand = new Page<>(page, rows);
        if (StringUtils.isNotBlank(key)) {
            brandQueryWrapper.like("name", "%" + key + "%").or().eq("letter", key);
        }

       if (StringUtils.isNotBlank(soryBy)){
           brandQueryWrapper.orderBy(true, desc,soryBy);
       }


        brandQueryWrapper.setEntity(brand);


        Page<Brand> brandPage = this.brandMapper.selectPage(pageBrand, brandQueryWrapper);

        return new PageResult<Brand>(brandPage.getTotal(), brandPage.getRecords());
    }
}
