package xyz.maojun.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.content.service.ContentService;
import xyz.maojun.mapper.TbContentMapper;
import xyz.maojun.pojo.TbContent;
import xyz.maojun.pojo.TbContentExample;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public EgouResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return EgouResult.ok();
    }


    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);
        return tbContents;
    }

}
