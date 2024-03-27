package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Material;
import com.zhangqi.fmsbackend.service.impl.MaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    MaterialServiceImpl materialService;

    //查询材料图标
    @RequestMapping("/findForChart")
    public List<Material> findMaterialForChart() {
        return materialService.findMaterialForChart();
    }

    //根据原材料类型查询，默认全体原材料
    @RequestMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        return materialService.selectMaterial(pageNum, pageSize, search);
    }

    //根据原料编号material_id删除原料信息
    @RequestMapping("/delete/{materialId}")
    public Result<?> deleteMaterial(@PathVariable Integer materialId) {
        return materialService.deleteMaterial(materialId);
    }

    //更新原料信息
    @RequestMapping("/update")
    public Result<?> updateMaterial(@RequestBody Material material) {
        return materialService.updateMaterial(material);
    }

    //添加货物信息
    @RequestMapping("/insert")
    public Result<?> insertMaterial(@RequestBody Material material) {
        return materialService.insertMaterial(material);
    }
}
