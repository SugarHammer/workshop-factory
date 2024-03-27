<template>
  <div style="padding-left:10px; padding-right: 10px; width: calc(100vw - 200px)">
    <!--    搜索区域-->
    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入产品类名关键字" style="width: 20%" clearable></el-input>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="load" plain>查询</el-button>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="add" plain>新增</el-button>
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="toChart">查看图表</el-button>
      <!-- 导出按钮 -->
      <el-button style="margin-left: 5px; background-color: #5defc1; color: white" @click="exportExcel">导出</el-button>
    </div>
    <el-table
      :data="tableData"
      border
      stripe
      style="width: 100%">
      <el-table-column
        prop="productId"
        label="货物编号"
        sortable>
      </el-table-column>
      <el-table-column
        prop="productType"
        label="货物种类">
      </el-table-column>
      <el-table-column
        prop="productPrice"
        label="货物价格(元)">
      </el-table-column>
      <el-table-column
        prop="productInventory"
        label="货物库存">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">

        <template #default="scope">
          <div style="display: flex; align-content: space-between">
            <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.row)"></el-button>
            <el-popconfirm
              title="确定删除吗？" @confirm="handleDelete(scope.row.productId)">
              <template #reference>
                <el-button type="primary" icon="el-icon-delete"></el-button>
              </template>
            </el-popconfirm>
          </div>
        </template>
      </el-table-column>
    </el-table>


    <div style="margin: 10px 0">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage4"
        :page-sizes="[2, 5, 10, 20,30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total">
      </el-pagination>
      <!--    弹窗-->
      <el-dialog
        title="货物信息修改/新增"

        :visible.sync="dialogVisible"
        width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="货物编号">
            <el-input v-model="form.productId" disabled></el-input>
          </el-form-item>
          <el-form-item label="货物类型">
            <el-input v-model="form.productType"></el-input>
          </el-form-item>
          <el-form-item label="货物单价(元)">
            <el-input v-model="form.productPrice"></el-input>
          </el-form-item>
          <el-form-item label="货物库存">
            <el-input v-model="form.productInventory"></el-input>
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
  import { export_json_to_excel } from '../../excel/Exropt2Excel'
  export default {
    name: 'Product',
    components: {},
    data () {
      return {
        form: {},
        search: '',
        currentPage4: 1,
        pageSize: 10,
        total: 0,
        dialogVisible: false,
        tableData: []
      }
    },
    created () {
      this.load()
    },
    methods: {
      load () {
        request.get('/system/product/find', {
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
        // this.form = JSON.parse((JSON.stringify(row)))
        this.form = {}//初始化表格
        this.dialogVisible = true
      },
      save () {
        if (this.form.productId) {//有productID即证明存在货物，则是更新操作
          request.put('/system/product/update', this.form).then(res => {
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
          request.put('/system/product/insert', this.form).then(res => {
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
      toChart () {
        this.$router.push('/home/productChart')
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
      handleDelete (productId) {
        request.delete('/system/product/delete/' + productId).then(res => {
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
      exportExcel() {
        var tHeader = ['货物编号', '货物种类', '货物价格','货物库存']
        var filterVal = ['productId', 'productType','productPrice','productInventory']
        var filename = '货物信息'
        var data = this.formatJson(filterVal, this.tableData)
        export_json_to_excel({
          header: tHeader,
          data,
          filename
        })
      },
      formatJson(filterVal, tableData) {
        return tableData.map(v => {
            return filterVal.map(j => {
              return v[j]
            })
          }
        )
      }
    }
  }
</script>

<style scoped>


</style>
