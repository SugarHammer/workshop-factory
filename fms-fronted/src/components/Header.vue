<template>
  <div style="height: 50px;width: 100%; line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: black;">车间工厂管理平台</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;">
      <el-button type="primary" @click="toLogin">注销</el-button>
<!--      <el-submenu>-->
<!--        <template slot="title">{{user.name}}</template>-->
<!--        <el-menu-item @click="toLogin">注销</el-menu-item>-->
<!--      </el-submenu>-->
      <!--        <el-dropdown>-->
<!--        <i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
<!--          <template #dropdown>-->
<!--            <el-dropdown-menu>-->
<!--              <el-dropdown-item @click="toPerson">个人信息</el-dropdown-item>-->
<!--              <el-dropdown-item @click="toLogin">退出系统</el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </template>-->
<!--      </el-dropdown>-->
    </div>
  </div>
</template>

<script>
  import request from '../utils/request'

export default {
  name: "Header",
  data(){
    return{
      user:{},
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}";
    this.user = JSON.parse(str);
    request.get("/system/user/"+this.user.account).then(res=>{
      if (res.code==='0'){
        this.user = res.data;
      }
    });
  },
  methods:{
    toLogin(){
      let that = this ;
      that.$router.replace('/login');
    },
  }
}
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
