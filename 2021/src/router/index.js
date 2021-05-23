import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '欢迎使用NJU问卷系统！',
    component: () => import('@/views/Home'),
    meta: {
      index: 0
    }
  },
  {
    path: '/login',
    name: '登录，开启探索之旅！',
    component: () => import('@/views/Login'),
    meta: {
      index: 1,
      keepAlive: true
    }
  },
  {
    path: '/signup',
    name: '注册，准备探索之旅！',
    component: () => import('@/views/Signup'),
    meta: {
      index: 1,
      keepAlive: true
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
