package xyz.maojun.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Description: Aliyun OSS
 * Created by maojun
 * Date: 2019/08/10
 * Time: 1:28 PM
 */
public class AliOSS {

    @Test
    public void testoSS() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAItzSkbvQnohjJ";
        String accessKeySecret = "DXFfOLhHkzA8kDrMhvq3VQDat7lSu5";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = new FileInputStream("/home/maojun/Documents/GitHub/qingcheng-mall/qingcheng-web-manager/src/main/webapp/img/1.jpg");
        ossClient.putObject("qingcheng-mall-project", "hello.jpg", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
