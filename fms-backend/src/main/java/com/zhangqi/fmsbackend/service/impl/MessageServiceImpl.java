package com.zhangqi.fmsbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Message;
import com.zhangqi.fmsbackend.mapper.MessageMapper;
import com.zhangqi.fmsbackend.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Result<?> selectMessage(Integer pageNum, Integer pageSize, Integer userId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("message_receiver_id",userId);
        Page selectPage = messageMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(selectPage);
    }

    @Override
    public Result<?> deleteMessage(Integer messageId) {
        messageMapper.delete(new QueryWrapper<Message>().eq("message_id",messageId));
//        messageMapper.deleteById(messageId);
        return Result.success();
    }

//    @Override
//    public Result<?> updateMessage(Message message) {
//        Result<Message> updateresult = new Result<>();
//        int updateNum=messageMapper.updateById(message);
//        if(updateNum!=0){
//            updateresult=Result.success();
//        }else {
//            updateresult=Result.error("-1","信息更新失败");
//        }
//        return updateresult;
//    }

    @Override
    public Result<?> insertMessage(Message message) {
        Result<Message> result = new Result<>();
        System.out.println(message);
        int insertNum=messageMapper.insert(message);
        return result.success();
    }
}
