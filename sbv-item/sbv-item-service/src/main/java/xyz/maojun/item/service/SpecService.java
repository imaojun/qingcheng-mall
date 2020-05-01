package xyz.maojun.item.service;

import xyz.maojun.pojo.SpecGroup;
import xyz.maojun.pojo.SpecParam;

import java.util.List;

public interface SpecService {
    List<SpecGroup> queryGroupsByCid(Long cid);

    List<SpecParam> queryParamsByGid(Long gid);
}
