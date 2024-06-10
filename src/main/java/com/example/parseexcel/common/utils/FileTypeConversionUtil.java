package com.example.parseexcel.common.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.UUID;

/**
 * 文件类型转换工具类
 * @author liuhuan
 * @date 2024/6/10
 */
public class FileTypeConversionUtil {

    private static String SOURCE_PATH;
    private static String TARGET_PATH;

    @Value("${file.source-path}")
    public void setSourcePath(String sourcePath){
        FileTypeConversionUtil.SOURCE_PATH = sourcePath;
    }

    @Value("${file.target-path}")
    public void setTargetPath(String targetPath){
        FileTypeConversionUtil.TARGET_PATH = targetPath;
    }

    /**
     * spring环境下调用
     */
    public static void videoToAudio(){
        videoToAudio(new File(SOURCE_PATH), new File(TARGET_PATH));
    }

    /**
     * 提取视频文件中音频
     * @param sourceFile
     * @param targetFile
     */
    private static void videoToAudio(File sourceFile, File targetFile) {
        if (sourceFile.isFile()){
            //file.getName() 获取的是带后缀的全文件名称
            String fileHeaderName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf('.'));
            FFmpegFrameGrabber frameGrabber1 = new FFmpegFrameGrabber(sourceFile);

            try {
                frameGrabber1.start();
                // 音频输出位置,当前默认使用视频的名称，（有一定重名概率，后续可优化）
                String targetPath = targetFile + fileHeaderName + ".mp3";

                FFmpegFrameRecorder recorder;
                recorder = new FFmpegFrameRecorder(targetPath, frameGrabber1.getAudioChannels());
                recorder.setFormat("mp3");
                recorder.setSampleRate(frameGrabber1.getSampleRate());
                recorder.setTimestamp(frameGrabber1.getTimestamp());
                recorder.setAudioQuality(0);

                recorder.start();
                Frame frame;
                while (true) {
                    frame = frameGrabber1.grab();
                    if (frame == null) {
                        break;
                    }
                    if (frame.samples != null) {
                        recorder.recordSamples(frame.sampleRate, frame.audioChannels, frame.samples);
                    }
                }
                recorder.stop();
                recorder.release();
                frameGrabber1.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            File[] files = sourceFile.listFiles();
            for (File file2 : files) {
                videoToAudio(file2, targetFile);
            }
        }

    }

    public static void main(String[] args) {
        //macos中不能使用~代替路径中的家目录
        videoToAudio(new File("/Users/huanliu/Documents/tempfile/video"),
                new File("~/Documents/tempfile/audio"));
    }
}
