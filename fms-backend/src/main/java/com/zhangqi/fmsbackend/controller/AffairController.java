package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Affair;
import com.zhangqi.fmsbackend.service.impl.AffairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/affair")
public class AffairController {
    @Autowired
    AffairServiceImpl affairService;

    //  根据事务状态查询，默认全部事务
    @RequestMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam Integer userId){
        return affairService.selectAffire(pageNum,pageSize, userId);
    }

//    @RequestMapping("/findAll")
//    public Result<?> findAllPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                                 @RequestParam(defaultValue = "10") Integer pageSize){
//        return affairService.selectAllAffire(pageNum,pageSize);
//    }
    //根据事务编号afire_id删除货物信息
    @RequestMapping("/delete/{affairId}")
    public Result<?> deleteAffair(@PathVariable Integer affairId){
        return affairService.deleteAffair(affairId);
    }

    //更新货物信息
    @RequestMapping("/update")
    public Result<?> updateAffair(@RequestBody Affair affair){

        return affairService.updateAffair(affair);
    }

    //添加货物信息
    @RequestMapping("/insert")
    public Result<?> insertAffair(@RequestBody Affair affair){
        return affairService.insertAffair(affair);
    }
}
