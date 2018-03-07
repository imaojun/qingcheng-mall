package xyz.maojun.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.EasyUITreeNode;
import xyz.maojun.mapper.TbItemCatMapper;
import xyz.maojun.pojo.TbItemCat;
import xyz.maojun.pojo.TbItemCatExample;
import xyz.maojun.sso.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setText(tbItemCat.getName());
            easyUITreeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(easyUITreeNode);
        }
        return resultList;
    }
}
