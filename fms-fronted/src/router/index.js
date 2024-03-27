import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name:'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/home',
    name:'Home',
    component: () => import('../views/Home.vue'),
    children : [
      {
        path:'user',
        name:'User',
        component: () => import('../components/home/User.vue')
      },
      {
        path:'product',
        name:'Product',
        component: () => import('../components/home/Product.vue')
      },
      {
        path:'productChart',
        name:'ProductChart',
        component: () => import('../components/home/ProductChart.vue')
      },
      {
        path:'materialChart',
        name:'MaterialChart',
        component: () => import('../components/home/MaterialChart.vue')
      },
      {
        path:'performance/:userId',
        name:'Performance',
        component: () => import('../components/home/Performance.vue')
      },
      {
        path:'message/:userId',
        name:'Message',
        component: () => import('../components/home/Message.vue')
      },
      {
        path:'material',
        name:'Material',
        component: () => import('../components/home/Material.vue')
      },
      {
        path:'affair/:userId',
        name:'Affair',
        component: () => import('../components/home/Affair.vue')
      },
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
