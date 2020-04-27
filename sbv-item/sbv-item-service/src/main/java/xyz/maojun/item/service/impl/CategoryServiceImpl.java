package xyz.maojun.item.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.item.mapper.CategoryMapper;
import xyz.maojun.pojo.Category;
import xyz.maojun.item.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * query Category list by parent id
     * @param pid
     * @return
     */
    @Override
    public List<Category> getCategoryListById(Long pid) {
        Category category = new Category();
        category.setParentId(pid);

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.setEntity(category);

        return categoryMapper.selectList(categoryQueryWrapper);
    }
}
