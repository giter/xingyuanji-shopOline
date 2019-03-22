package com.shopoline.xingyuanji.utils;

import com.shopoline.xingyuanji.common.ExceptionEnum;
import com.shopoline.xingyuanji.model.ImgUploadVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class UploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    public static ImgUploadVM upload(MultipartFile file, String path) throws Exception{

        ImgUploadVM imgUploadVM = new ImgUploadVM();
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image != null) {
            imgUploadVM.setSize(String.valueOf(image.getWidth())+'x'+String.valueOf(image.getHeight()));
        }
        if (file.isEmpty()){
            throw new Exception(ExceptionEnum.EXCEPTION_14.getDesc());
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replace("-", "");
        // 使用原文件名
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path).append(fileName).append(suffixName);
        String realPath = stringBuilder.toString();

        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            // 保存文件
            file.transferTo(dest);
            imgUploadVM.setImgFullName(fileName + suffixName);
            return imgUploadVM;
        } catch (Exception e) {
            throw new Exception(ExceptionEnum.EXCEPTION_26.getDesc());
        }
    }
}
