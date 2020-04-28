package xyz.maojun.upload.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.maojun.upload.service.UploadService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: upload service impl
 * @Created by maojun
 * @Date: 2020/04/28
 */
@Service
public class uploadServiceImpl implements UploadService {

    public static final List<String> FILE_TYPE = Arrays.asList("image/jpeg","image/jpg");
    public static final Logger LOGGER = LoggerFactory.getLogger(uploadServiceImpl.class);

    @Override
    public String uploadImae(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();

        if (!FILE_TYPE.contains(contentType)) {
            LOGGER.error("文件类型错误: {}", originalFilename);
            return null;
        }


        // check file is image
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.error("不是有效的图片: {}", originalFilename);
                return null;
            }

            file.transferTo(new File("/home/maojun/app/nginx/html/" + originalFilename));

            return "http://image.leyou.com/" + originalFilename;

        } catch (IOException e) {
            LOGGER.error("图片上传出错: {}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
