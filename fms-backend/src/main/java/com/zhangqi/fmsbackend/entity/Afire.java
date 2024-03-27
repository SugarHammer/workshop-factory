package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@TableName("t_afire")
public class Afire implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事务发布者编号
     */
    private Integer userId;

    /**
     * 事务状态
     */
    private String state;

    /**
     * 事务类型
     */
    private String afireType;

    /**
     * 事务内容
     */
    private String afireContent;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getAfireType() {
        return afireType;
    }

    public void setAfireType(String afireType) {
        this.afireType = afireType;
    }
    public String getAfireContent() {
        return afireContent;
    }

    public void setAfireContent(String afireContent) {
        this.afireContent = afireContent;
    }

    @Override
    public String toString() {
        return "Afire{" +
            "userId=" + userId +
            ", state=" + state +
            ", afireType=" + afireType +
            ", afireContent=" + afireContent +
        "}";
    }
}
