package xyz.maojun.item.listener;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import xyz.maojun.item.pojo.Item;
import xyz.maojun.pojo.TbItem;
import xyz.maojun.pojo.TbItemDesc;
import xyz.maojun.sso.service.ItemService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class HtmlGenListener implements MessageListener{
    @Value("${FREEMARKER_FILE_PATH}")
    private String FREEMARKER_FILE_PATH;
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            Long itemId = Long.valueOf(text);
            // 等待事务提交
            Thread.sleep(300);
            TbItem tbItem = itemService.getItemById(itemId);
            Item item = new Item(tbItem);
            TbItemDesc ItemDesc = itemService.getItemDescById(itemId);
            Map date = new HashMap();
            date.put("item", item);
            date.put("itemDesc", ItemDesc);
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Writer out = new FileWriter(FREEMARKER_FILE_PATH+itemId+".html");
            template.process(date, out);
            out.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
