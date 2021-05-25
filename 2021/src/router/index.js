import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: '欢迎来到NJU问卷系统！',
    component: () => import('@/views/index/Home'),
    meta: {
      index: 0
    }
  },
  {
    path: '/login',
    name: '登录，开启探索之旅！',
    component: () => import('@/views/index/Login'),
    meta: {
      index: 1,
      keepAlive: true
    }
  },
  {
    path: '/signup',
    name: '注册，准备探索之旅！',
    component: () => import('@/views/index/Signup'),
    meta: {
      index: 1,
      keepAlive: true
    }
  },
  {
    path: '/main',
    component: () => import('@/views/main/Main'),
    meta: {
      index: 1,
      keepAlive: true
    },
    children: [
      {
        path: 'profile',
        name: '个人中心',
        component: () => import('@/views/main/Profile'),
        meta: {
          keepAlive: true
        }
      },
      {
        path: '',
        name: 'NJU问卷系统',
        component: () => import('@/views/main/CreateQN'),
        meta: {
          keepAlive: true
        },
        children: [
          {
            path: 'detail',
            name: '创建问卷',
            component: () => import('@/views/main/CreateQN_detail')
          }
        ]
      },
      {
        path: 'searchQN',
        name: '查找问卷',
        component: () => import('@/views/main/SearchQN'),
        meta: {
          keepAlive: true
        }
      },
      {
        path: 'manageQN',
        name: '管理问卷',
        component: () => import('@/views/main/ManageQN'),
        meta: {
          keepAlive: true
        }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
