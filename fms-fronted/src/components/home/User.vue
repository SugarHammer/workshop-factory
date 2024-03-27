<template>
  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    &lt;!&ndash;        功能区域&ndash;&gt;-->
    <!--    <div style="margin: 10px 0">-->
    <!--      <el-button type="warning" plain @click="add">新增</el-button>-->
    <!--      <el-button type="primary" plain>导入</el-button>-->
    <!--      <el-button type="primary" plain>导出</el-button>-->
    <!--    </div>-->

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入员工姓名关键字" style="width: 20%" clearable></el-input>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
    </div>
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="userId"
        label="用户编号"
        sortable>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别">
      </el-table-column>
      <el-table-column
        prop="salary"
        label="薪资(元)">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系方式">
      </el-table-column>
      <el-table-column
        prop="job"
        label="岗位">
      </el-table-column>
      <el-table-column
        prop="hiredate"
        label="入职时间">
      </el-table-column>
      <!--      <el-table-column-->
      <!--        prop="role"-->
      <!--        label="权限">-->
      <!--      </el-table-column>-->
      <el-table-column
        label="权限">
        <template #default="scope">
          <span v-if="scope.row.role===1">超级管理员</span>
          <span v-if="scope.row.role===2">普通管理员</span>
          <span v-if="scope.row.role===3">无管理权限</span>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">

        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <!--            <el-button @click="handleEdit(scope.row)" type="primary">编辑</el-button>-->
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)"></el-button>
            <el-popconfirm
              title="确定删除吗？" @confirm="handleDelete(scope.row.userId)">
              <template #reference>
                <el-button type="primary" icon="el-icon-delete"></el-button>
              </template>
            </el-popconfirm>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div style="margin: 10px 0">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage4"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
      <!--    弹窗-->
      <el-dialog
        title="员工信息修改"

        :visible.sync="dialogVisible"
        width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男" value="男">男</el-radio>
            <el-radio v-model="form.sex" label="女" value="女">女</el-radio>
          </el-form-item>
          <el-form-item label="薪资">
            <el-input v-model="form.salary">元</el-input>
          </el-form-item>
          <el-form-item label="联系电话">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="岗位">
            <el-radio v-model="form.job" label="一线工人" value="一线工人">一线工人</el-radio>
            <el-radio v-model="form.job" label="车间主任" value="车间主任">车间主任</el-radio>
            <el-radio v-model="form.job" label="厂长" value="厂长">厂长</el-radio>
          </el-form-item>
          <el-form-item label="权限" v-if="this.user.role===1">
            <el-radio v-model="form.role" label="1" value="1">超级管理员</el-radio>
            <el-radio v-model="form.role" label="2" value="2">普通管理员</el-radio>
            <el-radio v-model="form.role" label="3" value="3">无管理权限</el-radio>
          </el-form-item>
          <el-form-item label="入职日期">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择入职日期" v-model="form.hiredate"
                              style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import request from '../../utils/request'

  export default {
    name: 'User',
    components: {},
    data () {
      return {
        form: {},
        search: '',
        currentPage4: 1,
        pageSize: 10,
        total: 0,
        dialogVisible: false,
        tableData: [],
        addFlag: '',
        user: {}
      }
    },
    created () {
      this.load()
      this.confirm()
    },
    methods: {
      confirm () {
        let str = sessionStorage.getItem('user') || '{}'
        this.user = JSON.parse(str)
        request.get('/system/user/' + this.user.account).then(res => {
          if (res.code === '0') {
            this.user = res.data
          }
        })
      },
      load () {
        request.get('/system/user/findUser', {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            search: this.search
          }
        }).then(res => {
          this.tableData = res.data.records
          this.total = res.data.total
        })
      },
      add () {
        this.dialogVisible = true
        this.form = {}
      },
      save () {
        request.put('/system/user/update', this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '更新成功'
            })
          } else {
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
          this.load()//刷新表格数据
          this.dialogVisible = false//关闭弹窗
        })
      },
      handleEdit (row) {
        this.form = JSON.parse((JSON.stringify(row)))
        this.dialogVisible = true
      },
      handleSizeChange (pageSize) {//改变当前页面个数
        this.pageSize = pageSize
        this.load()
      },
      handleCurrentChange (pageNum) {//改变当前页码
        this.currentPage4 = pageNum
        this.load()
      },
      // currentPage4(){
      //
      // },
      handleDelete (userId) {
        console.log(userId)
        request.delete('/system/user/deleteUser/' + userId).then(res => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
          } else {
            this.$message({
              type: 'error',
              message: res.msg
            })
          }
          this.load()//重新加载
        })
      },
    }
  }
</script>

<style scoped>

</style>
