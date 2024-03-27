package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Afire;
import com.zhangqi.fmsbackend.mapper.AfireMapper;
import com.zhangqi.fmsbackend.service.IAfireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@Service
public class AfireServiceImpl extends ServiceImpl<AfireMapper, Afire> implements IAfireService {

    @Autowired
    AfireMapper afireMapper;

    @Override
    public Result<?> selectAffire(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("state",search);
        Page selectPage = afireMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deleteAfire(Integer afireId) {
//
// ));
        return Result.success();
    }

    @Override
    public Result<?> updateAfire(Afire afire) {
        return null;
    }

    @Override
    public Result<?> insertAfire(Afire afire) {
        return null;
    }
}
