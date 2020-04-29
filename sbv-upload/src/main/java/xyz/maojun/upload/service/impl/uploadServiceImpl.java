package xyz.maojun.upload.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.maojun.upload.service.UploadService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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


    @Autowired
    private FastFileStorageClient fastFileStorageClient;

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

            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            return "http://image.leyou.com/" + storePath.getFullPath();

        } catch (IOException e) {
            LOGGER.error("图片上传出错: {}",originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
