<template>
  <div class="home">
    <div class="choose-box">
      <div class="greeting-banner">
        <h2>
          欢迎使用NJU问卷系统！
        </h2>
      </div>
      <div class="choose-area">
        <div :class="{active: mouseOnLogin}" class="choose-btn" @click="toLogin" @mouseover="mouseOnLogin=true"
             @mouseleave="mouseOnLogin=false">登录，开启探索之旅
        </div>
        <div :class="{active: mouseOnSignup}" class="choose-btn" @click="toSignup" @mouseover="mouseOnSignup=true"
             @mouseleave="mouseOnSignup=false">注册，准备探索之旅
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      mouseOnLogin: false,
      mouseOnSignup: false,
    }
  },
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
.home .choose-box .choose-area .active {
  background: linear-gradient(120deg, #5acd57 0%, #9876de 100%);
}
</style>
