package xyz.maojun.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import xyz.maojun.dao.CategoryMapper;
import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Category;
import xyz.maojun.service.goods.CategoryService;

import java.util.List;
import java.util.Map;

/**
 * Description: CategoryServiceImpl
 * Created by maojun
 * Date: 2019/08/14
 * Time: 9:14 PM
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageResult<Category> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<Category> pageResult = (Page<Category>) categoryMapper.selectAll();
        return new PageResult<Category>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public List<Category> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        Page<Category> pageResult = (Page<Category>) categoryMapper.selectByExample(example);
        return new PageResult<Category>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);

    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


    /**
     * 构建查询条件
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 分类名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 是否显示
            if (searchMap.get("isShow") != null && !"".equals(searchMap.get("isShow"))) {
                criteria.andLike("isShow", "%" + searchMap.get("isShow") + "%");
            }
            // 是否导航
            if (searchMap.get("isMenu") != null && !"".equals(searchMap.get("isMenu"))) {
                criteria.andLike("isMenu", "%" + searchMap.get("isMenu") + "%");
            }

            // 分类ID
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 商品数量
            if (searchMap.get("goodsNum") != null) {
                criteria.andEqualTo("goodsNum", searchMap.get("goodsNum"));
            }
            // 排序
            if (searchMap.get("seq") != null) {
                criteria.andEqualTo("seq", searchMap.get("seq"));
            }
            // 上级ID
            if (searchMap.get("parentId") != null) {
                criteria.andEqualTo("parentId", searchMap.get("parentId"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }

        }
        return example;
    }

}
