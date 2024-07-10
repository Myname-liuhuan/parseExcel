package com.example.parseexcel.module.parseexcel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.parseexcel.dao.TransferDbMappingTableMapping;
import com.example.parseexcel.module.parseexcel.dao.dto.TransferDbMappingTableDTO;
import com.example.parseexcel.module.parseexcel.dao.model.TransferDbMappingTable;
import com.example.parseexcel.module.parseexcel.dao.vo.TransferDbMappingTableVO;
import com.example.parseexcel.module.parseexcel.service.TransferDbMappingService;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuhuan
 * @date 2024/4/5
 */
@Service
public class TransferDbMappingServiceImpl implements TransferDbMappingService {

    @Autowired
    TransferDbMappingTableMapping transferDbMappingTableMapping;

    public List<TransferDbMappingTable> list(TransferDbMappingTableDTO transferDbMappingTableDTO){
        Map<String, Object> queryMap = new HashMap<>();
        try {
            queryMap = PropertyUtils.describe(transferDbMappingTableDTO);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        QueryWrapper<TransferDbMappingTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(queryMap);
        return  transferDbMappingTableMapping.selectList(queryWrapper);
    }

    @Override
    public List<TransferDbMappingTableVO> listAll() {
        List<TransferDbMappingTableVO> volist = new ArrayList<>();
        List<TransferDbMappingTable> list = transferDbMappingTableMapping.selectList(null);
        for (TransferDbMappingTable transferDbMappingTable : list) {
            TransferDbMappingTableVO vo = new TransferDbMappingTableVO();
            BeanUtils.copyProperties(transferDbMappingTable, vo);
            volist.add(vo);
        }
        
        return  volist;
    }

    @Override
    public CommonResult<Page<TransferDbMappingTableVO>> pageList(Integer pageNum, Integer pageSize) {
        Page<TransferDbMappingTable> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<TransferDbMappingTable> query = new LambdaQueryWrapper<>();
        page = transferDbMappingTableMapping.selectPage(page, query);
        //封装为VO,(使用stream)
        List<TransferDbMappingTableVO> voList = page.getRecords().stream().map(record -> {
            TransferDbMappingTableVO vo = new TransferDbMappingTableVO();
            BeanUtils.copyProperties(record, vo);
            return vo;
        }).collect(Collectors.toList());

        Page<TransferDbMappingTableVO> voPage = new Page<>();
        BeanUtils.copyProperties(page, voPage);
        voPage.setRecords(voList);
        return CommonResult.success(voPage);
    }

    
}
