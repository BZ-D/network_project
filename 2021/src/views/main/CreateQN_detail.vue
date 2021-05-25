<template>
  <div id="create-detail">
    <!-- 最多20道题 -->
    <slot v-for="i in 20" :name="i+'_question'"></slot>
    <span id="addBtn">
      <img v-show="!addBtnFocus" :src="require('@/assets/img/createQN/加号.png')" alt=""
           @mouseover="addBtnFocus=true" @mouseleave="addBtnFocus=false">
      <img v-show="addBtnFocus" :src="require('@/assets/img/createQN/加号 (1).png')" alt=""
           @mouseover="addBtnFocus=true" @mouseleave="addBtnFocus=false"><br><br>
      <span id="problem-hint">点击此处添加一道题！</span><br><br>
    </span>

  </div>
</template>

<script>
export default {
  name: "CreateQN_detail",
  data() {
    return {
      addBtnFocus: false,
    }
  },
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
