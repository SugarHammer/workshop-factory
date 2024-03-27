package com.zhangqi.fmsbackend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangqi.fmsbackend.entity.User;
import com.zhangqi.fmsbackend.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.zhangqi.fmsbackend.samples.generator.system.mapper")
@SpringBootTest
class FmsBackendApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNotNull("user_id");
        userMapper.selectList(userQueryWrapper).forEach(System.out::println);
    }

}
