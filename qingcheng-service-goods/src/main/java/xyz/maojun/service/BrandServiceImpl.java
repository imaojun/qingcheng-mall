package xyz.maojun.service;

import com.alibaba.druid.sql.PagerUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.maojun.dao.BrandMapper;
import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Brand;
import xyz.maojun.service.goods.BrandService;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult<Brand> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<Brand> pageResult = (Page<Brand>) brandMapper.selectAll();
        return new PageResult<Brand>(pageResult.getTotal(), pageResult.getResult());
    }


}
