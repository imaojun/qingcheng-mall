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
//        Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");
        Map data= new HashMap<>();
        StudentTest student = new StudentTest(1, "jack", 12, "china");
        data.put("student", student);
        data.put("hello", "hello freemarker!");
        Writer out = new FileWriter(new File("E:\\student.html"));
        template.process(data, out);
        out.close();


    }
}
