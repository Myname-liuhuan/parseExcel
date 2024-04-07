package com.example.parseexcel.controller;

import com.example.parseexcel.dao.dto.TransferDbMappingTableDTO;
import com.example.parseexcel.dao.model.TransferDbMappingTable;
import com.example.parseexcel.dao.vo.TransferDbMappingTableVO;
import com.example.parseexcel.service.data.TransferDbMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author liuhuan
 * @date 2024/4/5
 */
@Controller
@RequestMapping("/transferDbMapping")
public class TransferDbMappingController {

    @Autowired
    TransferDbMappingService transferDbMappingService;

    /**
     * 列表查询
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<TransferDbMappingTable> list(TransferDbMappingTableDTO transferDbMappingTableDTO){
        return transferDbMappingService.list(transferDbMappingTableDTO);
    }

    /**
     * 列表查询所有
     */
    @RequestMapping("/listAll")
    @ResponseBody
    public List<TransferDbMappingTableVO> listAll(){
        return transferDbMappingService.listAll();
    }
}
