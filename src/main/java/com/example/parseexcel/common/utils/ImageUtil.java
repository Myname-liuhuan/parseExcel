package com.example.parseexcel.common.utils;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {
    
    /**
     * 生成缩略图
     * @param sourceImage 指向文件/目录的路径
     * @param targetDirectory 指向文件夹的路径
     */
    public static void generateThumbnails(File sourceImage, String targetDirectory){
        if (sourceImage.isFile()) {
            try {
                Thumbnails.of(sourceImage)
                    //设置缩略图大小，按等比缩放
                    .size(200, 200)
                    //将生成的缩略图写入文件
                    .toFile(new File(targetDirectory + "/" + sourceImage.getName()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            File[] files = sourceImage.listFiles();
            for (File file : files) {
                generateThumbnails(file, targetDirectory);
            }
        }
    }

}
