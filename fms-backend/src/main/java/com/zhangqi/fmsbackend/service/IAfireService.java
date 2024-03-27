package com.zhangqi.fmsbackend.service;

import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Afire;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
public interface IAfireService extends IService<Afire> {

    Result<?> selectAffire(Integer pageNum, Integer pageSize, String search);

    Result<?> deleteAfire(Integer affireId);

    Result<?> updateAfire(Afire afire);

    Result<?> insertAfire(Afire afire);
}
