package xyz.maojun.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.EasyUITreeNode;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.content.service.ContentCategoryService;
import xyz.maojun.mapper.TbContentCategoryMapper;
import xyz.maojun.pojo.TbContentCategory;
import xyz.maojun.pojo.TbContentCategoryExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentID) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentID);
        List<TbContentCategory> catList = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> nodeList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : catList) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            nodeList.add(node);
        }
        return nodeList;
    }

    @Override
    public EgouResult addContentCategory(long parentID, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentID);
        contentCategory.setName(name);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentID);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
        }
        contentCategoryMapper.updateByPrimaryKey(parent);
        return EgouResult.ok(contentCategory);
    }
}
