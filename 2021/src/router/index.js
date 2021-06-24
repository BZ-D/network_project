import Vue from 'vue'
import VueRouter from 'vue-router'
import { judgeLogin } from "@/util/auth";

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
      keepAlive: true,
      requireAuth: true
    },
    children: [
      {
        path: 'profile',
        name: '个人中心',
        component: () => import('@/views/main/Profile'),
        meta: {
          keepAlive: true,
          page: 'profile'
        }
      },
      {
        path: '',
        name: 'NJU问卷系统',
        component: () => import('@/views/main/CreateQN'),
        meta: {
          keepAlive: true,
          page: ''
        },
        children: [
          {
            path: 'detail',
            name: '创建问卷',
            component: () => import('@/views/main/CreateQN_detail'),
            meta: {
              page: ''
            }
          }
        ]
      },
      {
        path: 'search',
        name: '查找问卷',
        component: () => import('@/views/main/SearchQN'),
        meta: {
          keepAlive: true,
          page: 'search'
        }
      },
      {
        path: 'manage',
        name: '管理问卷',
        component: () => import('@/views/main/ManageQN'),
        meta: {
          keepAlive: true,
          page: 'manage',
          branch: ''
        },
        children: [
          {
            path: 'filled',
            name: '已填写的问卷',
            component: () => import('@/components/ManageQN/FilledQNs'),
            meta: {
              page: 'manage',
              branch: 'filled'  // branch用于在管理页面的不同分支页面
            }
          },
          {
            path: 'released',
            name: '已发布的问卷',
            component: () => import('@/components/ManageQN/ReleasedQNs'),
            meta: {
              page: 'manage',
              branch: 'released'
            }
          },
          {
            path: 'draft',
            name: '草稿箱',
            component: () => import('@/components/ManageQN/Drafts'),
            meta: {
              page: 'manage',
              branch: 'draft'
            }
          }
        ]
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  let legal = false;
  if (!to.path.startsWith("/main") || (to.path.startsWith("/main") && judgeLogin()))
    legal = true;

  if (legal) {
    next();
  } else {
    next({ path: '/' });
  }
});

export default router
