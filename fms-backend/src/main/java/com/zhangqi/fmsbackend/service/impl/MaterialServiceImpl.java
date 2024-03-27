package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Material;
import com.zhangqi.fmsbackend.mapper.MaterialMapper;
import com.zhangqi.fmsbackend.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {

    @Autowired
    MaterialMapper materialMapper;

    @Override
    public Result<?> selectMaterial(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("material_type",search);
        Page selectPage = materialMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deleteMaterial(Integer materialId) {
        materialMapper.delete(new QueryWrapper<Material>()
            .eq("material_id",materialId));
//        materialMapper.deleteById(materialId);
        return Result.success();
    }

    @Override
    public Result<?> updateMaterial(Material material) {
        Result<Material> updateresult = new Result<>();
        int updateNum=materialMapper.update(material,new QueryWrapper<Material>()
        .eq("material_id",material.getMaterialId()));
//        int updateNum=materialMapper.updateById(material);
        if(updateNum!=0){
            updateresult=Result.success();
        }else {
            updateresult=Result.error("-1","原料更新失败");
        }
        return updateresult;
    }

    @Override
    public Result<?> insertMaterial(Material material) {
        Result<Material> result = new Result<>();
        System.out.println("material="+material);
        Material res=materialMapper.selectOne(new QueryWrapper<Material>()
                .eq("material_type",material.getMaterialType()));
        if(res==null){
            int insertNum=materialMapper.insert(material);
            result=result.success();
        }else{
            result=result.error("-1","原料类型重复");
        }
        return result;
    }

    public List<Material> findMaterialForChart() {
        List<Material> materialList = materialMapper.selectList(new QueryWrapper<>());
        return materialList;
    }
}
