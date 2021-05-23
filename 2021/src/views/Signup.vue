<template>
  <div class="signup">
    <div class="signup-box">
      <h1>注册，准备探索之旅</h1>
      <div class="input-box">
        <div class="input-text">
          <span class="iconfont icon-mine"></span>
          <input type="text" placeholder="用户名">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock"></span>
          <input type="password" placeholder="密码">
        </div>
        <div class="input-text">
          <span class="iconfont icon-lock-again"></span>
          <input type="password" placeholder="重复密码">
        </div>
        <div class="input-btn">
          注 册
        </div>
        <div class="sign-up">
          已有账户？<a @click="toLogin">立即登录！</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Signup',
  components: {},
  methods: {
    toLogin() {
      this.$router.push('/login')
    }
  }
}
</script>
<style scoped>

</style>

