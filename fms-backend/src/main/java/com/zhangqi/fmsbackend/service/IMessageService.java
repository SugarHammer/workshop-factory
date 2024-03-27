package com.zhangqi.fmsbackend.service;

import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
public interface IMessageService extends IService<Message> {

    Result<?> selectMessage(Integer pageNum, Integer pageSize, Integer userId);

    Result<?> deleteMessage(Integer messageId);

//    Result<?> updateMessage(Message message);

    Result<?> insertMessage(Message message);
}
