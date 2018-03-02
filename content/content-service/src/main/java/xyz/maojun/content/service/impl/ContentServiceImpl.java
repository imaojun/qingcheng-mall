package xyz.maojun.content.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.maojun.common.jedis.JedisClient;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.JsonUtils;
import xyz.maojun.content.service.ContentService;
import xyz.maojun.mapper.TbContentMapper;
import xyz.maojun.pojo.TbContent;
import xyz.maojun.pojo.TbContentExample;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{
    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public EgouResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        // 缓存同步
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return EgouResult.ok();
    }


    @Override
    public List<TbContent> getContentListByCid(long cid) {
        // 查询缓存
        String jsonContent = jedisClient.hget("CONTENT_LIST", cid + "");
        // 检测
        if (StringUtils.isNotBlank(jsonContent)) {
            List<TbContent> tbContents = JsonUtils.jsonToList(jsonContent, TbContent.class);
            return tbContents;
        }
        // 如果缓存没有查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(example);
        // 将查询结果放到缓存中
        jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(tbContents));
        return tbContents;
    }

}
