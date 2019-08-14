package xyz.maojun.controller.file;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Description: file upload
 * Created by maojun
 * Date: 2019/08/10
 * Time: 11:42 AM
 */
@RestController
@RequestMapping("/upload")
public class uploadController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OSSClient ossClient;

    @PostMapping("/native")
    public String navtiveUpload(@RequestParam("file") MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("img");
        String filePath = path + "/" + file.getOriginalFilename();
        File destFile = new File(filePath);
        if (!destFile.getParentFile().exists()) {
            destFile.mkdirs();
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://127.0.0.1:9101/img/" + file.getOriginalFilename();
    }


    @PostMapping("/oss")
    public String ossUpload(@RequestParam("file") MultipartFile file,String space) {
        String bucketName = "qingcheng-mall-project";
        String originName =file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf("."));
        String frontName = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = space+"/"+frontName+ext;
        try {
            ossClient.putObject(bucketName, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://"+bucketName+".oss-cn-beijing.aliyuncs.com/" + fileName;
    }


}
