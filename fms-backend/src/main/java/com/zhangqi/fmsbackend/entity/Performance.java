package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@TableName("t_performance")
public class Performance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    private Integer userId;

    /**
     * 员工当日绩效
     */
    private Integer performance;

    /**
     * 员工日期
     */
    private LocalDateTime workDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
    public LocalDateTime getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDateTime workDate) {
        this.workDate = workDate;
    }

    @Override
    public String toString() {
        return "Performance{" +
            "userId=" + userId +
            ", performance=" + performance +
            ", workDate=" + workDate +
        "}";
    }
}
