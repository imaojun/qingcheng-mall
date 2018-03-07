package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        // test pojo
        StudentTest student = new StudentTest(1, "jack", 12, "china");
        data.put("student", student);

        // test list
        List<StudentTest> studentList = new ArrayList<>();
        studentList.add(new StudentTest(1, "jack", 11, "china"));
        studentList.add(new StudentTest(2, "mark", 12, "usa"));
        studentList.add(new StudentTest(3, "tom", 13, "japan"));
        studentList.add(new StudentTest(4, "jerry", 14, "china"));
        studentList.add(new StudentTest(5, "barry", 15, "china"));
        data.put("studentList", studentList);

        // test freemarker
        data.put("hello", "hello freemarker!");
        Writer out = new FileWriter(new File("E:\\student2.html"));
        template.process(data, out);
        out.close();


    }
}
