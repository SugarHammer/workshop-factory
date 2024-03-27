<template>


  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    发送消息按钮-->
    <div style="margin: 10px">
      <!--      <el-input v-model="search" placeholder="请输入查询绩效日期" style="width: 20%" clearable></el-input>-->
      <div class="block">
        <!--        <span class="demonstration">默认</span>-->
        <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="openAddDialog" plain>发送消息
        </el-button>

      </div>
    </div>

    <!--  表格区域-->
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="messageId"
        label="消息编号"
        sortable>
      </el-table-column>
      <el-table-column
        prop="userId"
        label="发送者编号">
      </el-table-column>
      <el-table-column
        prop="messageReceiverId"
        label="接收者编号">
      </el-table-column>
      <el-table-column
        prop="messageType"
        label="消息类型">
      </el-table-column>
      <el-table-column
        prop="messageContent"
        label="消息内容">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <!--            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)"></el-button>-->
            <el-popconfirm
              title="确定删除吗？" @confirm="handleDelete(scope.row.messageId)">
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
        title="消息发送"
        :visible.sync="dialogVisible"
        width="40%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="接收者编号" required>
            <el-input v-model="form.messageReceiverId"></el-input>
          </el-form-item>
          <el-form-item label="消息类型">
            <el-radio v-model="form.messageType" label="私信" value="私信">私信</el-radio>
            <el-radio v-model="form.messageType" v-if="this.user.role===1" label="宏观生产目标" value="宏观生产目标">宏观生产目标
            </el-radio>
            <el-radio v-model="form.messageType" v-if="this.user.role===2" label="详细生产安排" value="详细生产安排">详细生产安排
            </el-radio>
          </el-form-item>
          <el-form-item label="消息内容" required>
            <el-input
              type="textarea"
              autosize
              placeholder="请输入消息内容"
              v-model="form.messageContent">
            </el-input>
          </el-form-item>

        </el-form>
        <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="add">发送</el-button>
              </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import request from '../../utils/request'

  export default {
    name: 'Message',
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
        userId: this.$route.params.userId,
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
        request.get('/system/message/find', {
          params: {
            pageNum: this.currentPage4,
            pageSize: this.pageSize,
            userId: this.userId,
          }
        }).then(res => {
          this.tableData = res.data.records
          this.total = res.data.total
        })
      },
      openAddDialog () {
        this.form = {}//初始化表格
        this.dialogVisible = true
      },
      add () {
        this.form.userId = this.userId
        request.put('/system/message/insert', this.form).then(res => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '发送成功'
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
      handleDelete (messageId) {
        request.delete('/system/message/delete', {
          params: {
            messageId: messageId
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
      },
    }
  }
</script>

<style scoped>

</style>
