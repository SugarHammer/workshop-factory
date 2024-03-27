<template>
  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    搜索区域-->
    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入原料类名关键字" style="width: 20%" clearable></el-input>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="add" plain>新增</el-button>

    </div>
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="affairId"
        label="事务编号"
        sortable>
      </el-table-column>
      <el-table-column
        prop="affairType"
        label="事务种类">
      </el-table-column>
      <el-table-column
        prop="state"
        label="事务状态">
      </el-table-column>
      <el-table-column
        prop="affairContent"
        label="事务内容">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)">处理</el-button>
            <el-popconfirm
              title="确定删除吗？" @confirm="handleDelete(scope.row.affairId)">
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
        title="事务处理/新增"
        :visible.sync="dialogVisible"
        width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="事务类型">
            <el-radio v-model="form.affairType" label="请假申请" value="请假申请">请假申请</el-radio>
            <el-radio v-model="form.affairType" label="预支工资申请" value="预支工资申请">预支工资申请</el-radio>
            <el-radio v-model="form.affairType" label="设备报修申请" value="设备报修申请">设备报修申请</el-radio>
            <el-radio v-model="form.affairType" label="其他" value="其他">其他</el-radio>
          </el-form-item>
          <el-form-item label="事务内容">
            <el-input v-model="form.affairContent"></el-input>
          </el-form-item>
          <el-form-item label="事务状态">
            <el-radio-group v-model="form.state">
              <el-radio label="待处理" value="待处理"></el-radio>
              <el-radio label="已完成" value="已完成"></el-radio>
            </el-radio-group>
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
    name: 'Affair',
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
        request.get('/system/affair/find', {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            userId: this.userId
          }
        }).then(res => {
          this.tableData = res.data.records
          this.total = res.data.total
        })
      },
      add () {
        // this.form = JSON.parse((JSON.stringify(row)))
        this.form = {}//初始化表格
        this.dialogVisible = true
      },
      save () {
        if (this.form.affairId) {//有productID即证明存在货物，则是更新操作
          request.put('/system/affair/update', this.form).then(res => {
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
        } else {//新增操作
          this.form.userId = this.userId
          request.put('/system/affair/insert', this.form).then(res => {
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
        }
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
      handleDelete (materialId) {
        console.log(materialId)
        request.delete('/system/affair/delete/' + materialId).then(res => {
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
