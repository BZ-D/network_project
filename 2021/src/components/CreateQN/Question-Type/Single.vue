<template>
  <div id="single-question">
    <div class="options-show-area">
      <div class="question-title-show-area">
        <h2>{{ index + 1 }}、{{ $store.state.questions[index].title }}
          （{{ must }}）</h2>
      </div>

      <!-- 显示选项的地方 -->
      <ol type="A" class="options-list">
        <li v-for="(item, i) in options" :key="i">
          <input type="text" class="option-add-input" placeholder="请在此处输入选项内容" v-model="options[i]"
                 @blur="updateVuexOptions">
          <img v-show="!mouseOnSub[i]" :src="require('@/assets/img/createQN/减号.png')"
               alt="" @mouseover="mouseOnSub.splice(i, 1, true)">
          <img v-show="mouseOnSub[i]" :src="require('@/assets/img/createQN/减号 (1).png')"
               @click="delOneOption(i)"
               alt="" @mouseleave="mouseOnSub.splice(i, 1, false)">
        </li>
      </ol>


      <span class="add-option-btn" v-if="num_of_options_now<max_num_of_options">
        <img v-show="!mouseOnAdd" :src="require('@/assets/img/createQN/加号.png')"
             alt="" @mouseover="mouseOnAdd=true">
        <img v-show="mouseOnAdd" :src="require('@/assets/img/createQN/加号 (1).png')"
             @click="addOneOption"
             alt="" @mouseleave="mouseOnAdd=false"><br>
        <span class="option-add-hint">点击按钮增加一个选项（最多添加6个）</span>
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Single",
  props: {
    index: Number, // 当前问题的下标
  },
  data() {
    // 最多六个选项
    return {
      max_num_of_options: 6,
      num_of_options_now: 0,  // 目前选项个数
      mouseOnAdd: false,
      mouseOnSub: [],  // 用于每个选项后面的减号图标
      options: [],
      must: this.$store.state.questionAddingInfo.must === 'must' ? '必答' : '选答' // 是否必答
    }
  },
  methods: {
    addOneOption() {
      this.num_of_options_now++
      this.options.push('')
      this.mouseOnSub.push(false)
    },
    delOneOption(index) {
      // 点击某个选项后面的减号时，删去该选项
      this.num_of_options_now--
      this.options.splice(index, 1)

      // 及时更新store
      this.$store.commit('updateCertainQuestionOptions', {
        index: this.index,
        options: this.options
      })
    },
    updateVuexOptions() {
      // 当选项输入框失焦时，及时更新store.state中对应选择题的options属性
      this.$store.commit('updateCertainQuestionOptions', {
        index: this.index,
        options: this.options
      })
    }
  }
}
</script>

<style scoped>

#single-question .options-show-area {
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
  /*justify-content: center;*/

  width: 650px;
  /*height: 350px;*/
  border-radius: 25px;
  background: #fff;

  margin-bottom: 20px;
  padding-bottom: 20px;
}

#single-question .options-show-area .add-option-btn {
  margin-top: 10px;
}

#single-question .options-show-area .options-list img,
#single-question .options-show-area .add-option-btn img {
  width: 20px;
  height: 20px;
  cursor: pointer;
}

#single-question .options-show-area .question-title-show-area h2 {
  font-size: 25px;
  font-family: "STZhongsong", "STHeiti", serif;
  margin-top: 25px;
  margin-bottom: 10px;
}


#single-question .options-show-area .add-option-btn .option-add-hint {
  cursor: default;
}

#single-question .options-show-area .options-list {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

#single-question .options-show-area .options-list li {
  margin-bottom: 5px;
}

#single-question .options-show-area .options-list li input {
  border: 0;
  width: 400px;
  height: 40px;
  text-align: center;
  border-bottom: 1px solid #000;
  font-size: 20px;
}


</style>
