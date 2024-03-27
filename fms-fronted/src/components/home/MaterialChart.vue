<template>
  <div ref="materialChart" id="materialChart">

  </div>
</template>

<script>
  import request from '../../utils/request'
  import * as echarts from 'echarts'

  export default {
    name: 'MaterialChart',
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
        request.get('/system/material/findForChart',).then(res => {
          this.chartData = res
          this.chartData.forEach(item => {
            this.xList.push(item.materialType)
            this.yList.push(item.materialInventory)
            var chartDom = document.getElementById('materialChart')
            if (myChart != null && myChart != "" && myChart != undefined){
              myChart.dispose();
            }
            myChart = echarts.init(chartDom)
            var option
            this.xList=JSON.parse(JSON.stringify(this.xList))

            option = {
              title: {
                text: '原料库存表',
              },
              xAxis: {
                name: '原料类型',
                type: 'category',
                data: this.xList
              },
              yAxis: {
                name: '原料库存',
                type: 'value'
              },
              series: [
                {
                  data: this.yList,
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
