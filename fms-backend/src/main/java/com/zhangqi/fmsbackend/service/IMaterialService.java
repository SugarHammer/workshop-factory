package com.zhangqi.fmsbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Material;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
public interface IMaterialService extends IService<Material> {

    Result<?> selectMaterial(Integer pageNum, Integer pageSize, String search);

    Result<?> deleteMaterial(Integer materialId);

    Result<?> updateMaterial(Material material);

    Result<?> insertMaterial(Material material);

    List<Material> findMaterialForChart();
}
