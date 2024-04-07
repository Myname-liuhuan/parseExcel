package com.example.parseexcel.service.data.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.parseexcel.dao.TransferDbMappingTableMapping;
import com.example.parseexcel.dao.dto.TransferDbMappingTableDTO;
import com.example.parseexcel.dao.model.TransferDbMappingTable;
import com.example.parseexcel.dao.vo.TransferDbMappingTableVO;
import com.example.parseexcel.service.data.TransferDbMappingService;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
