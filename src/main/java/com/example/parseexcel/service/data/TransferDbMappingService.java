package com.example.parseexcel.service.data;

import com.example.parseexcel.dao.dto.TransferDbMappingTableDTO;
import com.example.parseexcel.dao.model.TransferDbMappingTable;
import com.example.parseexcel.dao.vo.TransferDbMappingTableVO;

import java.util.List;

/**
 * @author liuhuan
 * @date 2024/4/5
 */
public interface TransferDbMappingService {

    /**
     * 批量查询
     * @param transferDbMappingTableDTO
     * @return
     */
    List<TransferDbMappingTable> list(TransferDbMappingTableDTO transferDbMappingTableDTO);

    /**
     * 查询所有
     * @return
     */
    List<TransferDbMappingTableVO> listAll();
}
