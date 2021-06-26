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
      // const account_reg = /^[a-zA-Z0-9_]{6,20}$/
      // const passwd_reg = /^[a-zA-Z0-9_]{8,20}$/
      // if (!account_reg.test(this.accountNumber) || !passwd_reg.test(this.passwd)) {
      //   window.alert('用户名或密码错误！')
      //   return
      // }

      // 第二遍检查：后端检查，数据库中是否有此账户，以及账号和密码是否可以对应上
      // TODO

      login({acNumber: this.accountNumber, password: this.passwd}).then(res => {
        console.log(res)
        if (res === -1) {
          window.alert('账号或密码错误！')
        } else {
          window.alert('登录成功！')
          window.localStorage.setItem("userId", res.data.id);
          window.localStorage.setItem("userAcNumber", res.data.acNumber);
          window.localStorage.setItem("username", res.data.uname);
          window.localStorage.setItem("numOfRelease", res.data.numOfRelease);
          window.localStorage.setItem("numOfFilling", res.data.numOfFilling);
          window.localStorage.setItem("numOfDraft", res.data.numOfDraft);
          window.localStorage.setItem("isDraft", '0');
          this.$router.push('/main')
        }
      })



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
