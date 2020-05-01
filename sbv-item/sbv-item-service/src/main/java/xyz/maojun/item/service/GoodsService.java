package xyz.maojun.item.service;

import xyz.maojun.bo.SpuBo;
import xyz.maojun.pojo.PageResult;

public interface GoodsService {

    PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows);
}
