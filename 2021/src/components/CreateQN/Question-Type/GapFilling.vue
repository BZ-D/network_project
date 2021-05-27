<template>
  <div id="gap-filling-question">
    <div class="question-title-show-area">
      <h2>{{ index + 1 }}、(填空题、{{ must }})</h2>
      <h2> {{ $store.state.questions[index].title }} </h2>
      <!-- 删除题目按钮 -->
      <span :class="{active: mouseOnDelete}" class="delete-question-btn" @mouseover="mouseOnDelete=true"
            @mouseleave="mouseOnDelete=false">
        删除题目
    </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "GapFilling",
  props: {
    index: Number, // 当前问题的下标
  },
  data() {
    // 最多六个选项
    return {
      max_num_of_options: 6,
      mouseOnDelete: false,  // 用于删除题目按钮变化
      mouseOnAdd: false,
      mouseOnSub: [],  // 用于每个选项后面的减号图标
      options: [],
      must: this.$store.state.questionAddingInfo.must === 'must' ? '必答' : '选答', // 是否必答
    }
  },
  methods: {
    addOneOption() {
      this.options.push('')
      this.mouseOnSub.push(false)
    },
    delOneOption(index) {
      // 点击某个选项后面的减号时，删去该选项
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

#gap-filling-question .question-title-show-area {
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
  /*justify-content: center;*/

  width: 650px;
  /*height: 350px;*/
  border-radius: 25px;
  background: #fff;

  padding-top: 20px;
  margin-bottom: 20px;
  padding-bottom: 20px;
}

#gap-filling-question .question-title-show-area h2 {
  font-size: 25px;
  font-family: "STZhongsong", "STHeiti", serif;
}

#gap-filling-question .delete-question-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;


  margin: 40px 5% 0 auto;

  border: solid 2px red;
  width: 70px;
  height: 22px;

  color: red;
  font-size: 13px;
  cursor: pointer;
}

#gap-filling-question .active {
  color: #fff;
  background-color: #bd0101;
  border: solid 2px #bd0101;
}

</style>
