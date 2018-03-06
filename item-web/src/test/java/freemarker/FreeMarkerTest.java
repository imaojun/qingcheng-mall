package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class FreeMarkerTest {
    @Test
    public void testFreeMarker() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDirectoryForTemplateLoading(new File("E:\\egouMall\\item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("hello.ftl");
        Map data= new HashMap<>();
        data.put("hello", "hello freemarker!");
        Writer out = new FileWriter(new File("E:\\frl.html"));
        template.process(data, out);
        out.close();


    }
}
