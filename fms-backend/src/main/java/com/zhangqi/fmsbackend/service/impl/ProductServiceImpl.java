package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Product;
import com.zhangqi.fmsbackend.mapper.ProductMapper;
import com.zhangqi.fmsbackend.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public Result<?> insertProduct(Product product) {
        Result<Product> result = new Result<>();
        System.out.println("product="+product);
        Product res=productMapper.selectOne(new QueryWrapper<Product>()
                .eq("product_type",product.getProductType()));
        if(res==null){
            int insertNum=productMapper.insert(product);
            result=result.success();
        }else{
            result=result.error("-1","货物类型重复");
        }
        return result;
    }

    @Override
    public Result<?> deleteProduct(Integer product) {
        productMapper.deleteById(product);
        return Result.success();
    }

    @Override
    public Result<?> updateProduct(Product product) {
        Result<Product> updateresult = new Result<>();
        int updateNum=productMapper.updateById(product);
        if(updateNum!=0){
            updateresult=Result.success();
        }else {
            updateresult=Result.error("-1","货物更新失败");
        }
        return updateresult;
    }

    @Override
    public Result<?> selectProduct(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("product_type",search);
        Page<Object> page = new Page<>(pageNum, pageSize);
        Page selectPage = productMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public List<Product> findProductForChart(){
        List<Product> productList = productMapper.selectList(new QueryWrapper<>());
        return productList;
    }
}
