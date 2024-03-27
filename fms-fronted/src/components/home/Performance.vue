<template>
  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    搜索区域-->
    <div style="margin: 10px">
      <!--      <el-input v-model="search" placeholder="请输入查询绩效日期" style="width: 20%" clearable></el-input>-->
      <div class="block">
        <!--        <span class="demonstration">默认</span>-->
        <el-date-picker
          v-model="search"
          type="date"
          placeholder="选择日期">
        </el-date-picker>
        <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
        <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="openAddDialog" plain>新增
        </el-button>

      </div>
    </div>

    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="userId"
        label="员工编号"
        sortable>
      </el-table-column>
      <el-table-column
        prop="performance"
        label="绩效(件)">
      </el-table-column>
      <el-table-column
        prop="workDate"
        label="工作日期">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)"></el-button>
            <el-popconfirm
              title="确定删除吗？" @confirm="handleDelete(scope.row.workDate)">
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
        title="绩效信息修改/新增"

        :visible.sync="dialogVisible"
        width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="员工编号编号">
            <el-input v-model="form.userId"></el-input>
          </el-form-item>
          <el-form-item label="绩效(件)">
            <el-input v-model="form.performance"></el-input>
          </el-form-item>
          <el-form-item label="绩效日期">
            <el-date-picker
              v-model="form.workDate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
            >
          </el-form-item>
        </el-form>
        <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">更新</el-button>
                <el-button type="primary" @click="add">添加</el-button>
              </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import request from '../../utils/request'

  export default {
    name: 'Performance',
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
        userId: this.$route.params.userId
      }
    },
    created () {
      this.load()
    },
    methods: {
      load () {
        if (this.search === '') {
          request.get('/system/performance/findAll', {
            params: {
              pageNum: this.currentPage4,
              pageSize: this.pageSize,
              userId: this.userId,
            }
          }).then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
          })
        } else {
          request.get('/system/performance/findWithDate', {
            params: {
              pageNum: this.currentPage4,
              pageSize: this.pageSize,
              search: this.search,
              userId: this.userId,
            }
          }).then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
          })
        }
        this.search = ''
      },
      openAddDialog () {
        this.form = {}//初始化表格
        this.dialogVisible = true
      },
      add () {
        request.put('/system/performance/insert', this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '添加成功'
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
      save () {
        request.put('/system/performance/update', this.form).then(res => {
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
      handleDelete (workDateParam) {
        request.delete('/system/performance/delete', {
          params: {
            userId: this.userId,
            workDate: workDateParam
          }
        }).then(res => {
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
      }
    }
  }
</script>

<style scoped>

</style>
