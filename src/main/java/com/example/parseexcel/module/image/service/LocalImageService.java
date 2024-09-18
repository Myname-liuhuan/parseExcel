package com.example.parseexcel.module.image.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.image.dao.model.LocalImageInfo;

public interface LocalImageService extends IService<LocalImageInfo> {

    public CommonResult<Integer> refreshPath();
    
}
