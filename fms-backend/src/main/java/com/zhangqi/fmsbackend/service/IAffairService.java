package com.zhangqi.fmsbackend.service;

import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Affair;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
public interface IAffairService extends IService<Affair> {



    Result<?> selectAffire(Integer pageNum, Integer pageSize, Integer userId);

    Result<?> deleteAffair(Integer affairId);

    Result<?> updateAffair(Affair affair);

    Result<?> insertAffair(Affair affair);
}
