package com.example.parseexcel.module.music.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.parseexcel.common.result.CommonResult;
import com.example.parseexcel.module.music.dao.model.MusicInfo;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO;
import com.example.parseexcel.module.music.dao.vo.MusicInfoVO2;
import com.example.parseexcel.module.music.service.MusicInfoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/media/music")
public class MusicInfoController {
    @Autowired
    MusicInfoService musicInfoService;

    /**
     * 保存音乐信息
     * post请求一定记得要设置 @RequestBody
     * @param musicInfo
     * @return
     */
    @PostMapping("/saveMusicInfo")
    public CommonResult<Integer> saveMusicInfo(@RequestBody MusicInfo musicInfo){
        return musicInfoService.saveMusicInfo(musicInfo);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/pageList")
    public CommonResult<Page<MusicInfoVO>> pageList(MusicInfo musicInfo,Integer pageNum, Integer pageSize){
        return musicInfoService.pageList(musicInfo,pageNum == null? 1 :pageNum, pageSize == null? 10 : pageSize);
    }
    
    /**
     * 分页查询且关联歌手表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/pageListJoinSong")
    public CommonResult<Page<MusicInfoVO2>> pageListJoinSong(MusicInfo musicInfo,Integer pageNum, Integer pageSize){
        return musicInfoService.pageListJoinSong(musicInfo, pageNum == null || pageNum <= 0? 1 :pageNum, pageSize == null? 10 : pageSize);
    }

    /**
     * 通过id逻辑删除数据
     * @param id
     * @return
     */
    @PostMapping("/logicalDeleteById")
    public CommonResult<Integer> logicalDeleteById(@RequestBody Long id){
        return musicInfoService.logicalDeleteById(id);
    }

    /**
     * 通过id逻辑删除数据
     * @param id
     * @return
     */
    @PostMapping("/logicalBatchDeleteByIds")
    public CommonResult<Integer> logicalBatchDeleteByIds(@RequestBody List<MusicInfo> list){
        return musicInfoService.logicalBatchDeleteByIds(list);
    }
}
