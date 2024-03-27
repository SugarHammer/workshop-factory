package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.User;
import com.zhangqi.fmsbackend.mapper.UserMapper;
import com.zhangqi.fmsbackend.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    public Result<?> login(User user){
        User res = userMapper.selectOne(new QueryWrapper<User>()
                .eq("account",user.getAccount())
                .eq("pwd",user.getPwd()));
        if(res==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(user);

    }

    @Override
    public Result<?> register(User user) {
        Result<User> result = new Result<>();
        User res=userMapper.selectOne(new QueryWrapper<User>()
                .eq("account",user.getAccount()));
        System.out.println("res="+res);
        if(res==null){
            int insertNum=userMapper.insert(user);
            result=result.success();
        }else{
            result=result.error("-1","用户名重复");
        }
        return result;
    }

    @Override
    public Result<?> confirmUser(String account) {
        Result<User> confirmResult = new Result<>();
        User user=userMapper.selectOne(new QueryWrapper<User>()
                .eq("account",account));
        return confirmResult.success(user);
    }

    @Override
    public Result<?> selectUser(Integer pageNum, Integer pageSize, String search) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("name",search);
        Page selectPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deleteUser(Integer userId) {
        userMapper.deleteById(userId);
        return Result.success();
    }

    @Override
    public Result<?> updateUser(User user) {
        Result<User> updateresult = new Result<>();
        int updateNum=userMapper.updateById(user);
        if(updateNum!=0){
            updateresult=Result.success();
        }else {
            updateresult=Result.error("-1","更新失败");
        }
        return updateresult;
    }
}
