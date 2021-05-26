<template>
  <div id="create-detail">
    <!-- 最多20道题 -->
    <span id="addBtn">
      <img v-show="!addBtnFocus" :src="require('@/assets/img/createQN/加号.png')" alt=""
           @mouseover="addBtnFocus=true" @mouseleave="addBtnFocus=false">
      <img v-show="addBtnFocus" :src="require('@/assets/img/createQN/加号 (1).png')" alt=""
           @mouseover="addBtnFocus=true" @mouseleave="addBtnFocus=false" @click="$store.commit('toAdding')"><br><br>
      <span id="problem-hint">点击此处添加一道题</span><br><br>
    </span>
    <add_question_box v-show="$store.state.isAdding"></add_question_box>
  </div>
</template>

<script>
// 刷新页面时弹出“系统可能不会保存您的更改”
// window.onbeforeunload = function(e) {
//   const dialogText = 'Dialog text here';
//   e.returnValue = dialogText;
//   return dialogText;
// };

import add_question_box from '@/components/CreateQN/AddQuestionBox'

export default {
  name: "CreateQN_detail",
  components: {
    add_question_box
  },
  data() {
    return {
      addBtnFocus: false,  // 用于圆形蓝色加号的变化
    }
  },
  methods: {
  }

}
</script>

<style scoped>
#addBtn img {
  cursor: pointer;
}

#problem-hint {
  margin-bottom: 10px;
  font-weight: bold;
  font-size: 20px;
}

#addBtn img {
  width: 60px;
  height: 60px;
}

</style>
