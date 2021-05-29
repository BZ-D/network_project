<template>
  <div id="manage">
    <div class="manage-bar">
      <span :class="{mouseon: mouseOnFilled, active: page==='filled'}" class="filled" @mouseover="mouseOnFilled=true"
            @mouseleave="mouseOnFilled=false" @click="gotoFilled">已填写</span>
      <span class="separator">|</span>
      <span :class="{mouseon: mouseOnReleased, active: page==='released'}" class="released" @mouseover="mouseOnReleased=true"
            @mouseleave="mouseOnReleased=false" @click="gotoReleased">已发布</span>
      <span class="separator">|</span>
      <span :class="{mouseon: mouseOnDraft, active: page==='draft'}" class="draft" @mouseover="mouseOnDraft=true"
            @mouseleave="mouseOnDraft=false" @click="gotoDraft">草稿箱</span>
    </div>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "ManageQN",
  data() {
    return {
      mouseOnFilled: false,
      mouseOnReleased: false,
      mouseOnDraft: false,
      page: this.$route.meta.branch,  // 用于在已填写、已发布和草稿箱页面间跳转，默认为空
    }
  },
  methods: {
    gotoFilled() {
      this.page = 'filled'
      this.$router.push('/main/manage/filled')
    },
    gotoReleased() {
      this.page = 'released'
      this.$router.push('/main/manage/released')
    },
    gotoDraft() {
      this.page = 'draft'
      this.$router.push('/main/manage/draft')
    }
  }
}
</script>

<style scoped>

#manage .manage-bar {
  width: 350px;
  height: 35px;
  border-radius: 25px;
  background-color: #fff;

  display: flex;
  align-items: center;
  justify-content: center;
}

#manage .manage-bar span {
  font-size: 20px;
  cursor: pointer;
}

#manage .manage-bar .separator {
  margin-left: 15px;
  cursor: default;
  color: #bfbfbf;
}

#manage .manage-bar .released,
#manage .manage-bar .draft {
  margin-left: 15px;
}

#manage .manage-bar .mouseon {
  color: #8b8b8b;
}

#manage .manage-bar .active {
  color: #d4237a;
}
</style>
