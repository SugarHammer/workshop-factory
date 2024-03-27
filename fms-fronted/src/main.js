import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引入elementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//统一样式
import '@/assets/css/global.css'

Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
