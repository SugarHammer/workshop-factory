package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.Message;
import com.zhangqi.fmsbackend.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

//    @RequestMapping("/findWithDate")
//    public Result<?> findPageWithDate(@RequestParam(defaultValue = "1") Integer pageNum,
//                                      @RequestParam(defaultValue = "10") Integer pageSize,
//                                      @RequestParam String search,
//                                      @RequestParam Integer userId){
//        return performanceService.selectPerformance(pageNum,pageSize, search,userId);
//    }

    @RequestMapping("/test")
    public void test(){
        System.out.println("IN message!!!!!!!");
    }

    //根据用户员工编号来查询所接收消息
    @RequestMapping("/find")
    public Result<?> findPageAll(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 @RequestParam Integer userId){
        System.out.println("userId="+userId);
        return messageService.selectMessage(pageNum,pageSize,userId);
    }

    //根据消息编号删除消息记录
    @RequestMapping("/delete")
    public Result<?> deleteMessage(@RequestParam Integer messageId){
        return messageService.deleteMessage(messageId);
    }

    //更新消息记录
//    @RequestMapping("/update")
//    public Result<?> updateMessage(@RequestBody Message message){
//        return messageService.updateMessage(message);
//    }

    //发送消息
    @RequestMapping("/insert")
    public Result<?> insertMessage(@RequestBody Message message){
        System.out.println("in insert!!!!!!!!!!!!!!!");
        return messageService.insertMessage(message);
    }

}
