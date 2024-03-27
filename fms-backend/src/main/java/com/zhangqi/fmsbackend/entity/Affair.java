package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_affair")
public class Affair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事务编号
     */
    @TableId(value = "affair_id", type = IdType.AUTO)
    private Integer affairId;

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
    private String affairType;

    /**
     * 事务内容
     */
    private String affairContent;

    public Integer getAffairId() {
        return affairId;
    }

    public void setAffairId(Integer affairId) {
        this.affairId = affairId;
    }
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
    public String getAffairType() {
        return affairType;
    }

    public void setAffairType(String affairType) {
        this.affairType = affairType;
    }
    public String getAffairContent() {
        return affairContent;
    }

    public void setAffairContent(String affairContent) {
        this.affairContent = affairContent;
    }

    @Override
    public String toString() {
        return "Affair{" +
            "affairId=" + affairId +
            ", userId=" + userId +
            ", state=" + state +
            ", affairType=" + affairType +
            ", affairContent=" + affairContent +
        "}";
    }
}
