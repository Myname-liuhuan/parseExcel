package com.example.parseexcel.module.music.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicSinger;
import com.example.parseexcel.module.music.dao.vo.MusicSingerVO;
import com.example.parseexcel.module.music.service.MusicSingerService;

@RestController
@RequestMapping("/media/singer")
public class MusicSingerController {
    
    @Autowired
    MusicSingerService musicSingerService;
   
    /**
     * 获取所有歌手信息
     * @return
     */
    @GetMapping("/getList")
    public CommonResult<List<MusicSingerVO>> getList(MusicSinger musicSinger) {
        return musicSingerService.getList(musicSinger);
    }

    @PostMapping("/saveMusicSinger")
    public CommonResult<Integer> saveMusicSinger(@RequestBody MusicSinger musicSinger){
        return musicSingerService.saveMusicSinger(musicSinger);
    }

    @GetMapping("/pageList")
    public CommonResult<Page<MusicSingerVO>> pageList(MusicSinger musicSinger,Integer pageNum, Integer pageSize){
        return musicSingerService.pageList(musicSinger, pageNum == null? 1 :pageNum, pageSize == null? 10 : pageSize);
    }

    @PostMapping("/logicalDeleteById")
    public CommonResult<Integer> logicalDeleteById(@RequestBody Long id){
        return musicSingerService.logicalDeleteById(id);
    }

     public CommonResult<Integer> logicalBatchDeleteByIds(@RequestBody List<MusicSinger> list){
        return musicSingerService.logicalBatchDeleteByIds(list);
     }
    
}
