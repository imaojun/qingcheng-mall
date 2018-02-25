package xyz.maojun.content.service;

import xyz.maojun.common.util.EgouResult;
import xyz.maojun.pojo.TbContent;

import java.util.List;

public interface ContentService {
    EgouResult addContent(TbContent content);

    List<TbContent> getContentListByCid(long cid);
}
