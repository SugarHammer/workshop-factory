package com.zhangqi.fmsbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Performance;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
public interface IPerformanceService extends IService<Performance> {


    Result<?> selectPerformance(Integer pageNum, Integer pageSize, String search, Integer userId);

    Result<?> selectPerformance(Integer pageNum, Integer pageSize, Integer userId);

    Result<?> deletePerformance(Integer userId, String workDate);

    Result<?> updatePerformance(Performance performance);

    Result<?> insertPerformance(Performance performance);
}
