package com.zhangqi.fmsbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 员工账号
     */
    private String account;

    /**
     * 员工密码
     */
    private String pwd;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工性别
     */
    private String sex;

    /**
     * 员工工资
     */
    private BigDecimal salary;

    /**
     * 员工手机号
     */
    private String phone;

    /**
     * 员工岗位
     */
    private String job;

    /**
     * 员工入职时间
     */
    private LocalDateTime hiredate;

    /**
     * 员工权限{1：厂长，2：车间主任，3：工人}
     */
    private Integer role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public LocalDateTime getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", account=" + account +
            ", pwd=" + pwd +
            ", name=" + name +
            ", age=" + age +
            ", sex=" + sex +
            ", salary=" + salary +
            ", phone=" + phone +
            ", job=" + job +
            ", hiredate=" + hiredate +
            ", role=" + role +
        "}";
    }
}
