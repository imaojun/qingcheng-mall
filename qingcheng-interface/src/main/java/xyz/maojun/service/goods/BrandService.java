package xyz.maojun.service.goods;

import xyz.maojun.entity.PageResult;
import xyz.maojun.pojo.goods.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * search all brand
     * @return
     */
    List<Brand> findAll();

    /**
     * search brand by paging
     * @param page
     * @param size
     * @return
     */
    PageResult<Brand> findPage(int page, int size);

    /**
     * find brand by keyword
     * @param map
     * @return
     */
    List<Brand> findList(Map<String,String> map);

    /**
     * find brand by paging and keyword
     * @param map
     * @param page
     * @param size
     * @return
     */
    PageResult<Brand> findPage(Map<String,String> map, int page, int size);

    /**
     * find brand by id
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * add new brand
     * @param brand
     */
    void add(Brand brand);

    /**
     * update brand by id
     * @param brand
     */
    void update(Brand brand);

    /**
     * detele brand by id
     * @param id
     */
    void delete(int id);
}
