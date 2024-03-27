// 跨域配置
module.exports = {
  // devServer: {
  //   port:9999,
  //   proxy: {  //配置跨域
  //     '/system': {
  //       target: 'localhost:8888/',  //这里后台的地址模拟的;应该填写你们真实的后台接口
  //       changOrigin: true,  //允许跨域
  //       pathRewrite: {
  //         /* 重写路径，当我们在浏览器中看到请求的地址为：http://localhost:8080/api/core/getData/userInfo 时
  //           实际上访问的地址是：http://121.121.67.254:8185/core/getData/userInfo,因为重写了 /api
  //          */
  //         '/system': ''
  //       }
  //     },
  //   }
  // },
  devServer: {                //记住，别写错了devServer//设置本地默认端口  选填
    port: 9999,
    proxy: {                 //设置代理，必须填
      '/system': {              //设置拦截器  拦截器格式   斜杠+拦截器名字，名字可以自己定
        target: 'http://localhost:8888/',     //代理的目标地址
        changeOrigin: true,              //是否设置同源，输入是的
        pathRewrite: {                   //路径重写
          '^/system': ''                     //选择忽略拦截器里面的单词
        //  '^/api': ''//这里理解成用‘/api’代替target里面的地址，后面组件中我们掉接口时直接用api代替 比如我要调用'http://40.00.100.100:3002/user/add'，直接写‘/api/user/add’即可
        }
      }
    }
  }
}
