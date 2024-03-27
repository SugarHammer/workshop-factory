package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Affair;
import com.zhangqi.fmsbackend.mapper.AffairMapper;
import com.zhangqi.fmsbackend.service.IAffairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
@Service
public class AffairServiceImpl extends ServiceImpl<AffairMapper, Affair> implements IAffairService {

    @Autowired
    AffairMapper affairMapper;

    @Override
    public Result<?> selectAffire(Integer pageNum, Integer pageSize, Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        Page selectPage = affairMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deleteAffair(Integer affairId) {
        affairMapper.delete(new QueryWrapper<Affair>()
                .eq("affair_id",affairId));
        return Result.success();
    }

    @Override
    public Result<?> updateAffair(Affair affair) {
        Result<Affair> updateresult = new Result<>();
        int updateNum=affairMapper.update(affair,new QueryWrapper<Affair>()
                .eq("affair_id",affair.getAffairId()));
//        int updateNum=materialMapper.updateById(material);
        if(updateNum!=0){
            updateresult=Result.success();
        }else {
            updateresult=Result.error("-1","货物更新失败");
        }
        return updateresult;
    }

    @Override
    public Result<?> insertAffair(Affair affair) {
        Result<Affair> result = new Result<>();
        int insertNum = affairMapper.insert(affair);
        if (insertNum != 0) {
            result = result.success();
        } else {
            result = result.error("-1", "新增事务失败");
        }
        return result;
    }
//        Affair res=affairMapper.selectOne(new QueryWrapper<Material>()
//                .eq("affire_",material.getMaterialType()));
//        if(res==null){
//            int insertNum=materialMapper.insert(material);
//            result=result.success();
//        }else{
//            result=result.error("-1","原料类型重复");
//        }
}
