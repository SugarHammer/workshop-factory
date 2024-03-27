package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Performance;
import com.zhangqi.fmsbackend.service.impl.PerformanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    PerformanceServiceImpl performanceService;

    @RequestMapping("/findWithDate")
    public Result<?> findPageWithDate(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam String search,
                              @RequestParam Integer userId){
        return performanceService.selectPerformance(pageNum,pageSize, search,userId);
    }

    @RequestMapping("/test")
    public void test(){
        System.out.println("IN performance!!!!!!!");
    }

    @RequestMapping("/findAll")
    public Result<?> findPageAll(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "4") Integer userId){
        return performanceService.selectPerformance(pageNum,pageSize,userId);
    }
    //根据绩效员工编号(user_id)和绩效日期(datetime)删除绩效记录
    @RequestMapping("/delete")
    public Result<?> deletePerformance(@RequestParam Integer userId,
                                   @RequestParam String workDate){
        return performanceService.deletePerformance(userId,workDate);
    }

    //更新绩效信息
    @RequestMapping("/update")
    public Result<?> updatePerformance(@RequestBody Performance performance){
        return performanceService.updatePerformance(performance);
    }

    //添加绩效信息
    @RequestMapping("/insert")
    public Result<?> insertProduct(@RequestBody Performance performance){
        return performanceService.insertPerformance(performance);
    }
}
