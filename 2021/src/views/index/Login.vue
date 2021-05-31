<template>
  <div class="login">
    <div class="login-box">
      <h1>登录，开启探索之旅</h1>
      <div class="input-box">
        <div class="input-text">
          <span class="iconfont icon-mine"></span>
          <input type="text" placeholder="账号" v-model="accountNumber">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock"></span>
          <input type="password" placeholder="密码" v-model="passwd">
        </div>
        <div :class="{active: mouseOnLogin}" class="input-btn" @mouseover="mouseOnLogin=true"
             @mouseleave="mouseOnLogin=false" @click="login">
          登 录
        </div>
        <div class="sign-up">
          还没账户？<a :class="{signup_active: mouseOnSignup}" @click="toSignup" @mouseover="mouseOnSignup=true"
                  @mouseleave="mouseOnSignup=false">立即注册！</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import {login} from '@/api/user'

export default {
  name: 'Home',
  data() {
    return {
      mouseOnLogin: false,
      mouseOnSignup: false,

      accountNumber: '',
      passwd: ''
    }
  },
  components: {},
  methods: {
    toSignup() {
      this.$router.push('/signup')
    },
    toMain() {
      this.$router.push('/main')
    },
    login() {
      // TODO: 点击登录按钮后实现登录功能，首先检查是否输入，然后检查输入是否有误，然后检查数据库中是否有该用户，无误后登录

      if (this.accountNumber.length === 0) {
        window.alert('请输入用户名！')
        return
      }
      if (this.passwd.length === 0) {
        window.alert('请输入密码！')
        return
      }

      // 第一遍检查：前端检查，如果不符合正则要求直接提示错误
      const account_reg = /^[a-zA-Z0-9_]{6,20}$/
      const passwd_reg = /^[a-zA-Z0-9_]{8,20}$/
      if (!account_reg.test(this.accountNumber) || !passwd_reg.test(this.passwd)) {
        window.alert('用户名或密码错误！')
        return
      }

      // 第二遍检查：后端检查，数据库中是否有此账户，以及账号和密码是否可以对应上
      // TODO

      // login({accountNumber: this.accountNumber, password: this.passwd}).then(res => {
      //   console.log(res)
      //   // 期望后端返回
      //   // res: 是一个对象，内容如下
      //   // {
      //   // userId: int 用户在数据库中的id号,
      //   // accountNumber: String 账号,
      //   // nickname: String 昵称,
      //   // releasedQN: [] 该用户已发布问卷（包含问卷的详细问题，以及该问卷的所有填写情况）,
      //   // filledQN: [] 该用户已填写的问卷（包含此用户填写该问卷的详细情况）,
      //   // drafts: [] 该用户的草稿问卷（包含问卷的详细问题）
      //   // }
      //   if (成功请求到了数据) {
      //     界面显示登录成功
      //     this.$store.commit('setUserInfo', res)  // 更新vuex里的用户信息
      //     this.toMain() // 跳转到main界面，可以开始使用系统
      //   } else {
      //     // 没有成功请求到数据
      //     界面显示登录失败
      //   }
      // })



    }
  }
}

// TODO: 点击登录按钮后，核实账号密码是否为空、账号密码是否正确

</script>
<style scoped>
.login .login-box .input-box .sign-up .signup_active {
  text-decoration: underline;
}
</style>
