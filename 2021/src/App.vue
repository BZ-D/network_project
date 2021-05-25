<template>
  <div id="app">
    <transition :name="transitionName">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      transitionName: ''
    }
  },
  watch: {
    $route(to, from) {
      if (to.meta.index > from.meta.index) {
        // 前进
        this.transitionName = 'slide-left'
      } else if (to.meta.index < from.meta.index) {
        // 后退
        this.transitionName = 'slide-right'
      } else {
        this.transitionName = 'slide-horizon'
      }
    }
  }
}
</script>

<style>
@import "~@/assets/css/transition.css";
@import "~@/assets/css/index/Signup/page.css";
@import "~@/assets/css/index/Signup/iconfont.css";
@import "~@/assets/css/index/Login/page.css";
@import "~@/assets/css/index/Login/iconfont.css";
@import "~@/assets/css/index/Home/index.css";

html, body {
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
