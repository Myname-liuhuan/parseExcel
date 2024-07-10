package com.example.parseexcel.common.utils;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtil {

    private static String FIRST_SOURCE_DIRECTORY;
    private static String FIRST_TARGET_DIRECTORY;

    
    /**
     * 生成缩略图
     * @param sourceImage 指向文件/目录的路径
     * @param targetDirectory 指向文件夹的路径
     */
    private static void generateThumbnails(File sourceImage){
        if (sourceImage.isFile()) {
            try {
                Thumbnails.of(sourceImage)
                    //设置缩略图大小，按等比缩放
                    .size(200, 200)
                    //将生成的缩略图写入文件
                    .toFile(new File(sourceImage.getAbsolutePath().replace(FIRST_SOURCE_DIRECTORY, FIRST_TARGET_DIRECTORY)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            File[] files = sourceImage.listFiles();
            for (File file : files) {
                generateThumbnails(file);
            }
        }
    }

    /**
     * 生成缩略图,且包含子文件夹
     * @param sourceDirectoy
     * @param targetDirectory
     */
    public static void generateThumbnails(String sourceDirectoy, String targetDirectory){
        File sourceImage = new File(sourceDirectoy);
        File targetImage = new File(targetDirectory);
        if (!sourceImage.isDirectory()) {
            System.out.println(sourceDirectoy + " is not a directory");
            return;
        }
        if (!targetImage.isDirectory()) {
            System.out.println(targetDirectory + " is not a directory");
            return;
        }
        FIRST_SOURCE_DIRECTORY = sourceImage.getAbsolutePath();
        FIRST_TARGET_DIRECTORY = targetImage.getAbsolutePath();
        generateThumbnails(sourceImage);
    }

}
