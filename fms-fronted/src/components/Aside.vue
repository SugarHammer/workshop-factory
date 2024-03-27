<template>
  <el-menu
    style="width: 200px;min-height: calc(100vh - 50px);background:linear-gradient(0deg,#1deca4,#61b9dc)"
    :uniqueOpened="true"
    default-active="/news"
    class="el-menu-vertical-demo"
    router
    background-color="inherit"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="/home/user" v-if="this.user.role===1" >用户管理</el-menu-item>
<!--    <el-menu-item index="/home/product" v-if="this.user.role===1||this.user.role===2">货物管理</el-menu-item>-->
<!--    <el-menu-item index='/home/material' v-if="this.user.role===1||this.user.role===2">原料管理</el-menu-item>-->
    <el-submenu v-if="this.user.role===1||this.user.role===2" index="">
      <template slot="title">
        <span>库存管理</span>
      </template>
      <el-menu-item index="/home/product">货物管理</el-menu-item>
      <el-menu-item index="/home/material">原料管理</el-menu-item>
    </el-submenu>
    <el-menu-item :index="'/home/performance/'+this.user.userId" v-if="this.user.role===1||this.user.role===2">绩效管理</el-menu-item>
    <el-menu-item :index="'/home/message/'+this.user.userId">通知管理</el-menu-item>
    <el-menu-item :index="'/home/affair/'+this.user.userId">个人事务</el-menu-item>

  </el-menu>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Aside",
  data(){
    return{
      user:{},
    }
  },
  created() {
    let userStr =sessionStorage.getItem("user") || '{}';
    this.user = JSON.parse(userStr)
// 请求后端服务器，确认当前登录用户的合法性
    request.get("/system/user/"+this.user.account).then(res=>{
      if (res.code==='0'){
        this.user = res.data;
      }
    });
  },

  methods: {

  }
}
</script>

<style scoped>

</style>
