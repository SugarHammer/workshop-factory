<template>
  <div style="width: 100%;height:100vh; background: linear-gradient(90deg,#3cadeb,#9c88ff);display: flex;justify-content: center;align-items: center">
    <div style="width: 400px; height: 700px;box-shadow: 0 5px 15px rgba(0, 0, 0, .8); display: flex;justify-content: center;align-items: center;">
      <el-form :model="form" size="mini" style="height: 100% ;width: 300px;" ref="form" :rules="rules" >
        <div style="width: 100%;height: 40px;margin-top:10px;line-height: 40px;text-align: center">
          <span style="color: white;font-weight: 900;font-size: 30px;">Register</span>
        </div>
        <el-form-item style="margin-top: 20px"  label="姓名" required>
          <el-input v-model="form.name"  prefix-icon="el-icon-user-solid" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="account" label="账号" >
          <el-input v-model="form.account"  prefix-icon="el-icon-user-solid" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" >
          <el-input v-model="form.pwd" prefix-icon="el-icon-lock" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm" label="确认密码">
          <el-input v-model="form.confirm" prefix-icon="el-icon-lock" placeholder="请确认密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="年龄" required>
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" required>
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="性别" required>
          <el-radio v-model="form.sex" label="男" value="男">男</el-radio>
          <el-radio v-model="form.sex" label="女" value="女">女</el-radio>
        </el-form-item>

        <el-form-item >
          <el-button required style="color: white; border-radius: 30px;width: 100%;height: 50px;background: linear-gradient(90deg,#3cadeb,#9c88ff);" @click="register">注册</el-button>
        </el-form-item>
        <el-form-item >
          <el-button required style="color: white; border-radius: 30px;width: 100%;height: 50px;background: linear-gradient(90deg,#3cadeb,#9c88ff);" @click="toLogin">返回登陆</el-button>
        </el-form-item>
<!--        <el-form-item style="text-align: center; color: white;">-->
<!--          <a @click="toLogin" style="font-size: 16px;font-weight: 500;opacity: 0.8;text-decoration: underline">返回登录</a>-->
<!--        </el-form-item>-->
      </el-form>
    </div>
  </div>
</template>

<script>
  import request from "@/utils/request";

  export default {
    name: "Login",
    data(){
      return{
        form:{},
        rules: {
          account: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
          ],
          pwd: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ],
          confirm: [
            { required: true, message: '请输入验证密码', trigger: 'blur' },
          ],
        }
      }
    },
    methods:{
      toLogin(){
        this.$router.push("/Login")
      },
      register(){
        if (this.form.pwd !== this.form.confirm){
          this.$message({
            type: "success",
            message: "两次密码输入不一致"
          })
          return;
        }

        this.$refs['form'].validate((valid) => {
          if (valid) {
            request.post("/system/user/register",this.form).then(res => {
              if (res.code==='0'){
                this.$message({
                  type: "success",
                  message: "注册成功"
                })
                this.$router.push("/login");//注册成功后的页面跳转
              }else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });


      }
    }
  }
</script>

<style scoped>

</style>
