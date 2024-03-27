package com.zhangqi.fmsbackend.controller;


import com.zhangqi.fmsbackend.common.Result;
import com.zhangqi.fmsbackend.entity.User;
import com.zhangqi.fmsbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //用户登录
    @RequestMapping("/login")
    public Result<?> login(@RequestBody User user){//这只是传进来的user，并没有在数据库中进行查找
        return userService.login(user);

    }
    //用户注册
    @RequestMapping("/register")
    public Result<?> register(@RequestBody User user){
        return userService.register(user);
    }
    //用户验证
    @RequestMapping("/{account}")
    public Result<?>confirmUser(@PathVariable String account){
        return userService.confirmUser(account);
    }
    //    查询姓名员工，默认全体员工
    @RequestMapping("/findUser")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        return userService.selectUser(pageNum,pageSize, search);
    }

    //根据员工user_id删除员工信息
    @RequestMapping("/deleteUser/{userId}")
    public Result<?> deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    //更新员工信息
    @RequestMapping("/update")
    public Result<?> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    //test
    @RequestMapping("/test")
    public void test(){
        System.out.println("in test!!!!!!!!!!!!");
    }

    //    根据id查询
//    @GetMapping ("/{id}")
//    public Result<?> getById(@PathVariable Long id){
//        User user = userMapper.selectById(id);
//        return Result.success(user);
//    }
    //查看所有用户信息

//    //用户登录
//    @RequestMapping("/login")
//    @ResponseBody
//    public Result<?> login(){//这只是传进来的user，并没有在数据库中进行查找
//        System.out.println("IN!!!!!!");
//        User user = new User();
//        user.setAccount("1");
//        user.setPwd("123456");
////        System.out.println(userService.login(user));
//        return userService.login(user);
//    }
//    @PostMapping("/register")
//    public Result<?> register(@RequestBody User user){
//        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
//
//        User res = userService.
//
//        if (res!=null){
//            return Result.error("-1","用户名重复");
//        }
//        if (user.getPassword()==null){
//            user.setPassword("123456");
//        }
//        userMapper.insert(user);
//        return Result.success();
//    }
}
