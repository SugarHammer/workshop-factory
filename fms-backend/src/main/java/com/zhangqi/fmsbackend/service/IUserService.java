package com.zhangqi.fmsbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
public interface IUserService extends IService<User> {
    public Result<?> login(User user);

    Result<?> register(User user);

    Result<?> confirmUser(String account);

    Result<?> selectUser(Integer pageNum, Integer pageSize, String search);

    Result<?> deleteUser(Integer userId);

    Result<?> updateUser(User user);
}
