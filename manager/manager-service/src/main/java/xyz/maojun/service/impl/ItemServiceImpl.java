package xyz.maojun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maojun.common.pojo.EasyUIDateGridResult;
import xyz.maojun.common.util.EgouResult;
import xyz.maojun.common.util.IDUtils;
import xyz.maojun.mapper.TbItemDescMapper;
import xyz.maojun.mapper.TbItemMapper;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemDesc;
import xyz.maojun.pojo.TbItemExample;
import xyz.maojun.service.ItemService;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

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

    @Override
    public EgouResult addItem(TbItem item, String desc) {
        // 生成商品id
        long itemId = IDUtils.genItemId();
        //补全item属性
        item.setId(itemId);
        //商品状态 1正常 2下架 3删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        // 向商品表中插入数据
        tbItemMapper.insert(item);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        return EgouResult.ok();
    }
}
