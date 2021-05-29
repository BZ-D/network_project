<template>
  <div class="login">
    <div class="login-box">
      <h1>登录，开启探索之旅</h1>
      <div class="input-box">
        <div class="input-text">
          <span class="iconfont icon-mine"></span>
          <input type="text" placeholder="用户名">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock"></span>
          <input type="password" placeholder="密码">
        </div>
        <div class="input-btn" @click="toMain">
          登  录
        </div>
        <div class="sign-up">
          还没账户？<a @click="toSignup">立即注册！</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {},
  methods: {
    toSignup() {
      this.$router.push('/signup')
    },
    toMain() {
      this.$router.push('/main')
    }
  }
}

// TODO: 点击登录按钮后，核实账号密码是否为空、账号密码是否正确

</script>
<style scoped>

</style>
