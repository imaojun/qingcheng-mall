package xyz.maojun.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import xyz.maojun.pojo.Brand;

public interface BrandMapper extends BaseMapper<Brand> {

    @Insert("insert into tb_category_brand(category_id, brand_id) value (#{cid}, #{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cids,@Param("bid") Long id);
}
