package xyz.maojun.item.service;

import xyz.maojun.pojo.Category;

import java.util.List;

/**
 * @Description: Category Service
 * @Created by maojun
 * @Date: 2020/04/27
 */
public interface CategoryService {


    List<Category> getCategoryListById(Long pid);
}
