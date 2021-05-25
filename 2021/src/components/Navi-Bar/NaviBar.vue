<template>
  <!-- main下页面上方固定导航栏 -->
  <div id="navi-bar">
    <div class="wrap">
      <navi_bar_item v-for="item in navi_bar_items" :key="item.title" :title="item.title" :page="item.page"
                     :sel="selected" @navi-bar-item-click="changePage">
        <template #normalImg><img :src="item.normalImg" alt=""></template>
        <template #activeImg><img :src="item.activeImg" alt=""></template>
      </navi_bar_item>
    </div>
  </div>
</template>

<script>
import navi_bar_item from '@/components/Navi-Bar/NaviBar-item'

export default {
  name: "NaviBar",
  components: {
    navi_bar_item
  },
  data() {
    return {
      selected: '',
      navi_bar_items: [
        {
          title: '创建问卷',
          page: '',
          normalImg: require('@/assets/img/navi-bar/填写.png'),
          activeImg: require('@/assets/img/navi-bar/填写 (1).png')
        },
        {
          title: '查找问卷',
          page: 'searchQN',
          normalImg: require('@/assets/img/navi-bar/放大镜.png'),
          activeImg: require('@/assets/img/navi-bar/放大镜 (1).png')
        },
        {
          title: '管理问卷',
          page: 'manageQN',
          normalImg: require('@/assets/img/navi-bar/设置.png'),
          activeImg: require('@/assets/img/navi-bar/设置 (1).png')
        },
        {
          title: '个人中心',
          page: 'profile',
          normalImg: require('@/assets/img/navi-bar/个人.png'),
          activeImg: require('@/assets/img/navi-bar/个人 (1).png')
        }
      ]
    }
  },
  methods: {
    // 注意：methods里不要写箭头函数
    changePage(page) {
      this.selected = page
    }
  }
}
</script>

<style scoped>
.wrap {
  margin-top: 0;
  padding-bottom: 10px;
  width: 100%;
  border-bottom: 1px solid #eee;
  background: rgba(241, 241, 241, .9);
  display: flex;
  align-items: center;
  justify-content: space-around;
  font-size: 0;
}

.wrap img {
  width: 30px;
  height: 30px;
}

</style>
