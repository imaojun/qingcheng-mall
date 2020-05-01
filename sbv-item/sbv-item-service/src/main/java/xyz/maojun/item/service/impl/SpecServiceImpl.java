package xyz.maojun.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.item.mapper.SpecGroupMapper;
import xyz.maojun.item.mapper.SpecParamMapper;
import xyz.maojun.item.service.SpecService;
import xyz.maojun.pojo.SpecGroup;
import xyz.maojun.pojo.SpecParam;

import java.util.List;

/**
 * @Description: Spec service
 * @Created by maojun
 * @Date: 2020/05/01
 */

@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {

        QueryWrapper<SpecGroup> specGroupQueryWrapper = new QueryWrapper<>();
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        specGroupQueryWrapper.setEntity(specGroup);
        return this.specGroupMapper.selectList(specGroupQueryWrapper);
    }

    @Override
    public List<SpecParam> queryParamsByGid(Long gid) {
        QueryWrapper<SpecParam> specParamQueryWrapper = new QueryWrapper<>();
        SpecParam specParam = new SpecParam();
        specParam.setCid(gid);
        specParamQueryWrapper.setEntity(specParam);
        return this.specParamMapper.selectList(specParamQueryWrapper);
    }
}
