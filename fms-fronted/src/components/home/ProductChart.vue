<template>
  <div ref="productChart" id="productChart">

  </div>
</template>

<script>
  import * as echarts from 'echarts'
  import request from '../../utils/request'

  export default {
    name: 'ProductChart',
    data () {
      return {
        chartData: [],
        xList: [],
        yList: []
      }
    },
    created () {
      // this.show()
    },
    methods: {
      // load () {
      //
      // },
      show () {
        var myChart;
        const xList=[];
        const yList=[];
        request.get('/system/product/findForChart',).then(res => {
          this.chartData = res
          this.chartData.forEach(item => {
            this.xList.push(item.productType)
            this.yList.push(item.productInventory)
            var chartDom = document.getElementById('productChart')
            if (myChart != null && myChart != "" && myChart != undefined){
              myChart.dispose();
            }
            myChart = echarts.init(chartDom)
            var option
            this.xList=JSON.parse(JSON.stringify(this.xList))

            option = {
              title: {
                text: '货物库存表',
              },
              xAxis: {
                name: '货物类型',
                type: 'category',
                data: this.xList
              },
              yAxis: {
                name: '货物库存(件)',
                type: 'value'
              },
              series: [
                {
                  data: this.yList,
                  // data: [120, 200, 150, 80, 70, 110, 130],
                  type: 'bar',
                  showBackground: true,
                  backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                  }
                }
              ]
            }
            myChart.setOption(option)
          })
        })

      }
    },
    mounted () {
      setTimeout(()=>{
        this.show()
      },500)
    }
  }
</script>

<style scoped>
  #productChart {
    width: 500px;
    height: 500px;
  }
</style>
