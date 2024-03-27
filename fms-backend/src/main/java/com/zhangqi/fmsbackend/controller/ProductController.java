package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Product;
import com.zhangqi.fmsbackend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @RequestMapping("/findForChart")
    public List<Product> findProductForChart(){
        return productService.findProductForChart();
    }
    //    根据货物类型查询，默认全体货物
    @RequestMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        return productService.selectProduct(pageNum,pageSize, search);
    }

    //根据货物编号product_id删除货物信息
    @RequestMapping("/delete/{productId}")
    public Result<?> deleteProduct(@PathVariable Integer productId){
        return productService.deleteProduct(productId);
    }

    //更新货物信息
    @RequestMapping("/update")
    public Result<?> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    //添加货物信息
    @RequestMapping("/insert")
    public Result<?> insertProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }

}
