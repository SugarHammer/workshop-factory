package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Performance;
import com.zhangqi.fmsbackend.mapper.PerformanceMapper;
import com.zhangqi.fmsbackend.service.IPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@Service
public class PerformanceServiceImpl extends ServiceImpl<PerformanceMapper, Performance> implements IPerformanceService {

    @Autowired
    PerformanceMapper performanceMapper;

    @Override
    public Result<?> selectPerformance(Integer pageNum, Integer pageSize, String search, Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("user_id",userId);
        queryMap.put("work_date",search);
        queryWrapper.allEq(queryMap);
        Page selectPage = performanceMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> selectPerformance(Integer pageNum, Integer pageSize, Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        Page selectPage = performanceMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deletePerformance(Integer userId, String workDate) {
        HashMap<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("user_id",userId);
        deleteMap.put("work_date",workDate);
        performanceMapper.deleteByMap(deleteMap);
        return Result.success();
    }

    @Override
    public Result<?> updatePerformance(Performance performance) {
        Result<Performance> updateresult = new Result<>();
        int updateNum=performanceMapper.update(performance,new QueryWrapper<Performance>()
            .eq("user_id",performance.getUserId())
            .eq("work_date",performance.getWorkDate())
            .eq("performance",performance.getPerformance()));
        if(updateNum!=0){
            updateresult=Result.success();
        }else {
            updateresult=Result.error("-1","更新失败");
        }
        return updateresult;
    }

    @Override
    public Result<?> insertPerformance(Performance performance) {
        Result<Performance> result = new Result<>();
        Performance res=performanceMapper.selectOne(new QueryWrapper<Performance>()
            .eq("user_id",performance.getUserId())
            .eq("work_date",performance.getWorkDate()));
        if(res==null){
            int insertNum=performanceMapper.insert(performance);
            result=result.success();
        }else{
            result=result.error("-1","绩效记录重复");
        }
        return result;
    }
}
