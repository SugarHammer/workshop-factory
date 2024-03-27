package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-19
 */
@TableName("t_message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer messageId;

    /**
     * 消息发布者编号
     */
    private Integer userId;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息接收者编号
     */
    private Integer messageReceiverId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public Integer getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(Integer messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    @Override
    public String toString() {
        return "Message{" +
            "messageId=" + messageId +
            ", userId=" + userId +
            ", messageType=" + messageType +
            ", messageContent=" + messageContent +
            ", messageReceiverId=" + messageReceiverId +
        "}";
    }
}
