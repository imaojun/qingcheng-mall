package xyz.maojun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.mapper.TbItemMapper;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemExample;
import xyz.maojun.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(long itemid) {
        // 根据主键查询
//        TbItem item = tbItemMapper.selectByPrimaryKey(itemid);
//        return item;
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andIdEqualTo(itemid);
        // 执行查询
        List<TbItem> list = tbItemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EasyUIDateGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> list = tbItemMapper.selectByExample(example);

        EasyUIDateGridResult result = new EasyUIDateGridResult();
        result.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total= pageInfo.getTotal();
        result.setTotal(total);
        return result;
    }
}
