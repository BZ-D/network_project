<template>
  <!-- QN is alias of Questionnaire -->
  <div id="create">
    <!-- 如果正确填写了问卷标题并点击立即创建，进入detail具体题目页面，隐藏create-index盒子 -->
    <div v-if="!gotoDetail" id="create-index">
      <div class="start-box">
        <img :src="require('@/assets/img/createQN/纸笔.png')" alt="">
        <span>
        现在就来<br>
        创建一个全新的问卷！
      </span>
      </div>
      <div class="title-filling-box">
        <span class="title-filling-box-hint">请输入问卷标题</span><br><br>
        <input type="text" v-model="title" class="title-filling-box-input"
               placeholder="请在此处输入问卷标题，最多25个字">
        <!-- 注意一个细节：动态绑定的class要放到前面，固定class放到后面 -->
        <span :class="{beyond_limit: title.length>25 || title.length===0}"
              class="title-length-count">  {{ title.length }}</span>/25
      </div>
      <div id="creat-btn" @click="toCreateDetail" :class="{active: isBtnActive}"
           @mouseover="isBtnActive=true" @mouseleave="isBtnActive=false">立 即 创 建</div>
    </div>
    <!-- 如果正确填写了问卷标题并点击立即创建，进入detail具体题目页面 -->
    <router-view v-if="gotoDetail"></router-view>
  </div>

</template>

<script>
import detail from '@/views/main/CreateQN_detail'

// 刷新页面时弹出“系统可能不会保存您的更改”
window.onbeforeunload = function(e) {
  const dialogText = 'Dialog text here';
  e.returnValue = dialogText;
  return dialogText;
};

export default {
  name: "CreateQN",
  components: {
    detail
  },
  data() {
    return {
      title: '',
      gotoDetail: false,
      isBtnActive: false
    }
  },
  methods: {
    toCreateDetail() {
      // 点击 立即创建 后，跳转到添加题目的页面/detail
      if (this.title === '') {
        window.alert('请输入问卷标题后再开始创建！')
        return
      } else if (this.title.length > 25) {
        window.alert('问卷标题最多为25个字！')
        return
      }
      this.gotoDetail = true
      this.$store.commit('fillTitle', this.title)
      this.$router.push('/main/detail')
    }
  }
}
</script>

<style scoped>
#create-index {
  width: 800px;
  border-radius: 25px;
  height: 500px;
  margin: 0 auto;
  background: rgba(0, 0, 0, .5);
}

#create-index .start-box {
  padding-top: 30px;
  display: flex;
  flex-grow: 1;
  align-items: center;
  justify-content: center;
}

.start-box span {
  font-size: 40px;
  font-weight: bold;
  font-family: "STHeiti", "STZhongsong", serif;
  line-height: normal;
  text-align: left;
  color: #fff;
  text-shadow: 1px 1px 1px #000000;
}

#create-index .start-box img {
  width: 200px;
  height: 200px;
}

#create-index .title-filling-box {
  width: 100%;
}

#create-index .title-filling-box .title-filling-box-hint {
  font-size: 20px;
  color: #fff;
  text-shadow: #000000 1px 1px 1px;
}

#create-index .title-filling-box .title-filling-box-input {
  width: 70%;
  border: 0;
  border-bottom: solid 1px #fff;
  height: 40px;
  background-color: #ffffff00;
  color: #fff;
  font-size: 20px;
  text-align: center;
  font-weight: lighter;
}

#create-index .title-filling-box .beyond_limit {
  color: #b60505;
}

#creat-btn {
  color: #fff;
  text-shadow: #000000 1px 1px 1px;

  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  cursor: pointer;

  margin: 60px auto 0;

  width: 200px;
  height: 45px;
  border-radius: 25px;
  background: linear-gradient(120deg, #9c7ae3 0%, #7eec7d 100%);
  font-size: 22px;
}

#create-index .active {
  background: linear-gradient(120deg, #9876de 0%, #5acd57 100%);
}
</style>
