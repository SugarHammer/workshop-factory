package com.zhangqi.fmsbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Product;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
public interface IProductService extends IService<Product> {

    Result<?> insertProduct(Product product);

    Result<?> deleteProduct(Integer product);

    Result<?> updateProduct(Product product);

    Result<?> selectProduct(Integer pageNum, Integer pageSize, String search);

    public List<Product> findProductForChart();
}
