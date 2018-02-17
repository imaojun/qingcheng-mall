package xyz.maojun.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.maojun.mapper.TbItemMapper;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemExample;

import java.util.List;

public class PageHelpTest {
    @Test
    public void testPageHelper(){
       // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        // 获取代理对象
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        // 设置分页信息
        PageHelper.startPage(1,10);
        // 设置查询条件
        TbItemExample example = new TbItemExample();
        // 执行查询
        List<TbItem> list = tbItemMapper.selectByExample(example);
        // 获取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        // 查询分页结果
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(list.size());


    }
}
