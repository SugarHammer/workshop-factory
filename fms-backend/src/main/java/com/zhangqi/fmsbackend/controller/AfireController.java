package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Afire;
import com.zhangqi.fmsbackend.service.impl.AfireServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/afire")
public class AfireController {

    @Autowired
    AfireServiceImpl afireService;

    //  根据事务状态查询，默认全部事务
    @RequestMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        return afireService.selectAffire(pageNum,pageSize, search);
    }

    //根据事务编号afire_id删除货物信息
    @RequestMapping("/delete/{affireId}")
    public Result<?> deleteAfire(@PathVariable Integer affireId){
        return afireService.deleteAfire(affireId);
    }

    //更新货物信息
    @RequestMapping("/update")
    public Result<?> updateAfire(@RequestBody Afire afire){

        return afireService.updateAfire(afire);
    }

    //添加货物信息
    @RequestMapping("/insert")
    public Result<?> insertAfire(@RequestBody Afire afire){
        return afireService.insertAfire(afire);
    }
}
