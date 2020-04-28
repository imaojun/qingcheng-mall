package xyz.maojun.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: upload service
 * @Created by maojun
 * @Date: 2020/04/28
 */


public interface UploadService {
    String uploadImae(MultipartFile file);
}
