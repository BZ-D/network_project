<template>
  <div class="home">
    <div class="choose-box">
      <div class="greeting-banner">
        <h2>
          欢迎使用NJU问卷系统！
        </h2>
      </div>
      <div class="choose-area">
        <div class="choose-btn" @click="toLogin">登录，开启探索之旅</div>
        <div class="choose-btn" @click="toSignup">注册，准备探索之旅</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  methods: {
    toLogin() {
      this.$router.push('/login')
    },
    toSignup() {
      this.$router.push('signup')
    }
  },
}
</script>

<style scoped>

</style>
