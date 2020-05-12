package com.nuc.finish.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 尉一飞
 * @Description
 * @Date 创建于 2020/4/3 17:12
 */
@Component
public class FileUploadUtil {

    private static String filePath;

    @Value("${file.address}")
    public void setFilePath(String address) {
        filePath = address;
    }

    public static String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        //文件后缀名
        String fileExt = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + fileExt;
        File f = new File(filePath + "/" + fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            return null;
        }
        return "/video/" + fileName;
    }
}
