<template>
  <div id="create-detail">

    <div class="mask" v-show="$store.state.isAdding">
      <!-- 作用是突出显示添加题目窗口 -->
    </div>

    <!-- 最多20道题 -->
    <h2 class="qn-title">{{ $store.state.titleOfQN }}</h2>
    <new_question v-for="(item, index) in $store.state.questions" :key="index" :index="index"></new_question>

    <span id="addBtn" v-show="!$store.state.isAdding&&$store.state.numOfQuestions<20">
      <img v-show="!addBtnFocus" :src="require('@/assets/img/createQN/加号.png')" alt=""
           @mouseover="addBtnFocus=true">
      <img v-show="addBtnFocus" :src="require('@/assets/img/createQN/加号 (1).png')" alt=""
           @mouseleave="addBtnFocus=false" @click="$store.commit('toAdding')"><br><br>
      <span id="problem-hint">点击此处添加一道题</span><br><br>
    </span>


    <div class="cancel-and-confirm-area">
      <!-- 在所有题目的最下方，添加题目按钮的上方显示 -->
      <span :class="{active: isCancelQNBtnActive}" class="cancel-questionnaire-btn"
            @mouseover="isCancelQNBtnActive=true" @mouseleave="isCancelQNBtnActive=false">丢 弃 问 卷</span>
      <span :class="{active: isDraftQNBtnActive}" class="draft-questionnaire-btn" @mouseover="isDraftQNBtnActive=true"
            @mouseleave="isDraftQNBtnActive=false">存 为 草 稿</span>
      <span :class="{active: isConfirmQNBtnActive}" class="confirm-questionnaire-btn"
            @mouseover="isConfirmQNBtnActive=true" @mouseleave="isConfirmQNBtnActive=false">发 布 问 卷</span>
    </div>

    <add_question_box v-show="$store.state.isAdding"></add_question_box>
  </div>
</template>

<script>
// 刷新页面时弹出“系统可能不会保存您的更改”
window.onbeforeunload = function (e) {
  const dialogText = 'Dialog text here';
  e.returnValue = dialogText;
  return dialogText;
};

import add_question_box from '@/components/CreateQN/AddQuestionBox'
import new_question from '@/components/CreateQN/NewQuestion'

export default {
  name: "CreateQN_detail",
  components: {
    add_question_box,
    new_question
  },
  data() {
    return {
      addBtnFocus: false,  // 用于圆形蓝色加号的变化
      isCancelQNBtnActive: false,
      isDraftQNBtnActive: false,
      isConfirmQNBtnActive: false
    }
  },
  methods: {}

}
</script>

<style scoped>
#choose-type-box {
  z-index: 999;
}

#create-detail .mask {
  z-index: 997;

  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;

  position: fixed;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.49);
}

#create-detail .qn-title {
  text-align: center;
  margin: 0 auto 20px;
  width: 650px;
  height: 40px;

  color: #fff;
  text-shadow: 1px 1px 1px #000;
  font-size: 25px;
  font-family: "STZhongsong", "STHeiti", serif;


  background: rgba(0, 0, 0, .3);
}


#addBtn img {
  cursor: pointer;
}

#problem-hint {
  margin-bottom: 10px;
  font-weight: bold;
  font-size: 20px;
  cursor: default;
}

#addBtn img {
  width: 60px;
  height: 60px;
}

#create-detail .cancel-and-confirm-area {
  width: 800px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;

}

#create-detail .cancel-and-confirm-area span {
  cursor: pointer;

  display: flex;
  align-items: center;
  justify-content: center;

  font-size: 20px;
  color: #fff;
  text-shadow: 1px 1px 1px #000;

  width: 150px;
  height: 40px;

  border-radius: 25px;
  background: linear-gradient(120deg, #98aee5 0%, #988dec 100%);

}

#create-detail .cancel-and-confirm-area .draft-questionnaire-btn,
#create-detail .cancel-and-confirm-area .confirm-questionnaire-btn {
  margin-left: 20px;
}

#create-detail .cancel-and-confirm-area .active {
  background: linear-gradient(120deg, #809ce3 0%, #8074ec 100%);
}

</style>
