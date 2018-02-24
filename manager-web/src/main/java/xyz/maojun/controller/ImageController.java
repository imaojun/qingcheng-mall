package xyz.maojun.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xyz.maojun.common.util.FastDFSClient;
import xyz.maojun.common.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {
    @Value("${IMAGE_URL_SERVER}")
    private String IMAGE_URL;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {

        try {
            FastDFSClient fastDFSClient = new FastDFSClient("E:\\egouMall\\manager-web\\src\\main\\resources\\properties\\client.properties");
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            url = IMAGE_URL + url;
            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);
            return JsonUtils.objectToJson(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "image upload failure");
            return JsonUtils.objectToJson(result);
        }
    }
}
