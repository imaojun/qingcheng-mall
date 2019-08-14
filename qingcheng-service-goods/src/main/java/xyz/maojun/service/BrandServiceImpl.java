package xyz.maojun.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import xyz.maojun.dao.BrandMapper;
import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Brand;
import xyz.maojun.service.goods.BrandService;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Brand> findList(Map<String, String> map) {
        Example example = getExample(map);
        return brandMapper.selectByExample(example);
    }

    @Override
    public PageResult<Brand> findPage(Map<String, String> map, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = getExample(map);
        Page<Brand> pages = (Page<Brand>) brandMapper.selectByExample(example);
        return new PageResult<Brand>(pages.getTotal(), pages.getResult());
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(int id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    private Example getExample(Map<String, String> map) {
        Example example = new Example(Brand.class);
        if ((map != null)) {
            Example.Criteria criteria = example.createCriteria();
            if (map.get("name") != null) {
                criteria.andLike("name", "%" + map.get("name") + "%");
            }
            if (map.get("letter") != null) {
                criteria.andEqualTo("letter", map.get("letter"));
            }
        }
        return example;
    }


}
