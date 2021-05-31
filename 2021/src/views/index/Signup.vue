<template>
  <div class="signup">
    <div class="signup-box">
      <h1>注册，准备探索之旅</h1>
      <div class="input-box">
        <div class="input-text">
          <span class="iconfont icon-mine"></span>
          <input type="text" placeholder="账号: 6-20位，由大小写字母、数字和下划线组成" v-model="accountNumber">
        </div>
        <div class="input-text">
          <span class="iconfont icon-nickname"></span>
          <input type="text" placeholder="用户昵称: 不超过15位，由大小写字母、数字、下划线和汉字组成" v-model="nickname">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock"></span>
          <input type="password" placeholder="密码: 8-20位，由大小写字母、数字和下划线组成" v-model="passwd_1">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock-again"></span>
          <input type="password" placeholder="请在此重复您的密码" v-model="passwd_2">
        </div>
        <div :class="{active: mouseOnSignup}" class="input-btn" @mouseover="mouseOnSignup=true"
             @mouseleave="mouseOnSignup=false" @click="signup">
          注 册
        </div>
        <div class="log-in">
          已有账户？<a :class="{login_active: mouseOnLogin}" @mouseover="mouseOnLogin=true"
                  @mouseleave="mouseOnLogin=false" @click="toLogin">立即登录！</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import { signup } from '@/api/user'

export default {
  name: 'Signup',
  components: {},
  data() {
    return {
      mouseOnLogin: false,
      mouseOnSignup: false,

      accountNumber: '',
      nickname: '',
      passwd_1: '',
      passwd_2: '',

    }
  },
  methods: {
    toLogin() {
      this.$router.push('/login')
    },
    signup() {
      // TODO: 用户点击注册按钮后，先检查输入是否合格，再检查是否与现有数据存在冲突，如果都正确，将注册信息存入数据库

      // 一. 检查输入
      // 1. 是否有某项没输入
      if (this.accountNumber.length === 0) {
        window.alert('请输入账号！')
        return
      }
      if (this.nickname.length === 0) {
        window.alert('请输入用户昵称！')
        return
      }
      if (this.passwd_1.length === 0) {
        window.alert('请输入密码！')
        return
      }
      if (this.passwd_2.length === 0) {
        window.alert('请重复密码！')
        return
      }

      // 2. 如果都输入，依次按规范检查
      const account_reg = /^[a-zA-Z0-9_]{6,20}$/
      const nickname_reg = /^[a-zA-Z0-9_\u4E00-\u9FA5]{1,15}$/
      const passwd_reg = /^[a-zA-Z0-9_]{8,20}$/

      if (!account_reg.test(this.accountNumber)) {
        window.alert('账号必须由大小写字母、数字和下划线组成，且不少于6位、不超过20位，请检查您的输入！')
        return
      }
      if (!nickname_reg.test(this.nickname)) {
        window.alert('用户昵称必须由大小写字母、数字、下划线和中文组成，不能超过15位，请检查您的输入！')
        return
      }
      if (!passwd_reg.test(this.passwd_1)) {
        window.alert('密码必须由大小写字母、数字和下划线组成，且不少于8位、不超过20位，请检查您的输入！')
        return
      }

      // 3. 检查重复密码是否和之前一样
      if (this.passwd_1 !== this.passwd_2) {
        window.alert('两次输入的密码不一致！')
        return
      }

      // 检查完毕，下面需要检测是否与数据库已有帐户存在冲突
      //
      // signup({accountNumber: this.accountNumber, nickname: this.nickname, password: this.passwd_2}).then(res => {
      //   console.log(res)
      //   if (账号 昵称 密码都合格，成功存到了数据库中) {
      //     界面显示注册成功，而后跳转到登陆页面
      //   } else {
      //     界面显示注册失败！具体失败原因由后端返回：
      //     1、账号已存在
      //     2、昵称已存在
      //     （输入不规范的情况上面代码已经判断过了，只需要判断这两种情况即可）
      //   }
      // })

      // 没有冲突，将注册信息存入数据库
    }
  }
}

// TODO: 点击注册按钮后，核实账号密码是否为空，账号、密码是否符合规范、两次密码是否一致

</script>
<style scoped>
.signup .signup-box .input-box .sign-up .login_active {
  text-decoration: underline;
}
</style>

