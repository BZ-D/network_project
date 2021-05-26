<template>
  <div id="main" class="main-body">
    <navi_bar id="navi-bar"></navi_bar>
    <div id="main-content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import navi_bar from '@/components/Navi-Bar/NaviBar'

export default {
  name: "Main",
  components: {
    navi_bar
  },
  data() {
    return {
    }
  },
}
</script>

<style scoped>
.main-body {
  display: flex;
  /*align-items: center;*/
  justify-content: center;
  min-height: 100vh;
  height: 100%;
  background-image: url(~@/assets/img/main_bg.jpg);
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
  overflow: auto;
}

#navi-bar {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
}

#main-content {
  margin-top: 100px;
}


</style>
