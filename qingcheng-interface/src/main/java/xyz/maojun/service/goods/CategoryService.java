package xyz.maojun.service.goods;

import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Category;

import java.util.List;
import java.util.Map;

/**
 * Description: CategoryService
 * Created by maojun
 * Date: 2019/08/14
 * Time: 9:16 PM
 */
public interface CategoryService {

    /**
     * find all catagory list
     * @return List
     */
    List<Category> findAll();

    public PageResult<Category> findPage(int page, int size);


    public List<Category> findList(Map<String,Object> searchMap);


    public PageResult<Category> findPage(Map<String,Object> searchMap,int page, int size);


    public Category findById(Integer id);

    public void add(Category category);


    public void update(Category category);


    public void delete(Integer id);

}
