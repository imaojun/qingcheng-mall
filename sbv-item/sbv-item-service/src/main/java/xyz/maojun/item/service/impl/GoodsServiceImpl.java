package xyz.maojun.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.bo.SpuBo;
import xyz.maojun.item.mapper.BrandMapper;
import xyz.maojun.item.mapper.CategoryMapper;
import xyz.maojun.item.mapper.SpuDetailMapper;
import xyz.maojun.item.mapper.SpuMapper;
import xyz.maojun.item.service.GoodsService;
import xyz.maojun.pojo.Brand;
import xyz.maojun.pojo.Category;
import xyz.maojun.pojo.PageResult;
import xyz.maojun.pojo.Spu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: Goods service
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {


        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();


        // search by word
        if (StringUtils.isNotBlank(key)) {
            spuQueryWrapper.like("title", "%" + key + "%");
        }

        // filter by saleable
        if (saleable != null) {
            spuQueryWrapper.eq("saleable", saleable);
        }

        // pageable
        Page<Spu> spuPage = new Page<>(page, rows);

        Page<Spu> spuPages = this.spuMapper.selectPage(spuPage, spuQueryWrapper);

        List<Spu> spus = spuPages.getRecords();

        List<SpuBo> spubos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);

            // query bname
            Brand brand = this.brandMapper.selectById(spu.getId());
            if (brand != null) {
                spuBo.setBname(brand.getName());
            }

            // query cname
            List<Category> categoryList = this.categoryMapper.selectBatchIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            List<String> names = categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
            spuBo.setCname(StringUtils.join(names, "-"));
            return spuBo;
        }).collect(Collectors.toList());


        return new PageResult<>(spuPage.getTotal(),spubos);
    }
}
