package com.example.parseexcel.module.image.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.parseexcel.common.config.AppConfig;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.image.dao.LocalImageInfoMapper;
import com.example.parseexcel.module.image.dao.model.LocalImageInfo;
import com.example.parseexcel.module.image.service.LocalImageService;

import lombok.extern.slf4j.Slf4j;

/**
 * IMAGE 本地图片服务实现
 */
@Slf4j
@Service
public class LocalImageServiceImpl extends ServiceImpl<LocalImageInfoMapper, LocalImageInfo> implements LocalImageService {
    
    @Autowired
    AppConfig appConfig;

    @Autowired
    LocalImageInfoMapper localImageInfoMapper;

    /**
     * 手动刷新本地图片信息到数据库
     */
    @Transactional
    @Override
    public CommonResult<Integer> refreshPath() {
        // 读取配置文件的参数
        String imagePath = appConfig.imagePath;
        List<LocalImageInfo> list =  new ArrayList<>();
        //读取图片信息到list
        File imageFile = new File(imagePath);
        if (!imageFile.isDirectory()) {
            return CommonResult.failed("图片路径配置错误！");
        }

        String prefixUrl = appConfig.prefixUrl;
        for(File file : imageFile.listFiles()){
            if (file.isFile()) {
                LocalImageInfo imageInfo = new LocalImageInfo();
                imageInfo.setImagePath(prefixUrl + file.getName());
                list.add(imageInfo);
            }
        } 

        //清空image表
        localImageInfoMapper.deleteAll();

          //插入数据
        this.saveBatch(list);
        //求出总结果数量
        Long countLong =  localImageInfoMapper.selectCount(null);
        Integer count = countLong == null? 0 : countLong.intValue();
        if (count != list.size()) {
            log.error("预计插入条数与实际插入条数不相等，请检查结果");
        }

        return CommonResult.success(count);
    }
    
}
