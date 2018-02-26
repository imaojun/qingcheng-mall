package xyz.maojun.content.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.content.service.ContentService;
import xyz.maojun.mapper.TbContentMapper;
import xyz.maojun.pojo.TbContent;
import xyz.maojun.pojo.TbContentExample;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public EgouResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return EgouResult.ok();
    }


    @Override
    public List<TbContent> getContentListByCid(long cid) {
        String jsonContent = jedisClient.hget("CONTENT_LIST", cid + "");
        if (StringUtils.isNotBlank(jsonContent)) {
            List<TbContent> tbContents = JsonUtils.jsonToList(jsonContent, TbContent.class);
            return tbContents;
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);
        jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(tbContents));
        return tbContents;
    }

}
