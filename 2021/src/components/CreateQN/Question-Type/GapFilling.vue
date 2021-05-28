<template>
  <div id="gap-filling-question">

    <div class="mask" v-show="isDeleting">
      <!-- 作用是突出显示确认是否删除题目窗口 -->
    </div>

    <div class="question-title-show-area">
      <h2>{{ index + 1 }}、(填空题、{{ must }})</h2>
      <h2> {{ $store.state.questions[index].title }} </h2>
      <!-- 删除题目按钮 -->
      <span :class="{active: mouseOnDelete}" class="delete-question-btn" @mouseover="mouseOnDelete=true"
            @mouseleave="mouseOnDelete=false" @click="isDeleting=true">
        删除题目
    </span>
    </div>

    <div class="confirm-deleting-question-box" v-show="isDeleting">
      <!-- 确认是否删除题目窗口 -->
      <span class="hint">确定要删除这道题目吗？</span><br>
      <div class="btn-area">
        <span :class="{cancel_active: mouseOnCancelDeleting}" class="cancel-btn" @click="isDeleting=false"
              @mouseover="mouseOnCancelDeleting=true" @mouseleave="mouseOnCancelDeleting=false">取 消</span>
        <span :class="{confirm_active: mouseOnConfirmDeleting}" class="confirm-btn" @click="deleteThisQuestion"
              @mouseover="mouseOnConfirmDeleting=true" @mouseleave="mouseOnConfirmDeleting=false">确 定</span>
      </div>
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
      isDeleting: false,
      options: [],

      mouseOnCancelDeleting: false,   // 删除题目窗口取消按钮变化
      mouseOnConfirmDeleting: false   // 删除题目窗口确认按钮变化
    }
  },
  computed: {
    must() {
      return this.$store.state.questions[this.index].must === 'must' ? '必答' : '选答' // 是否必答
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
    },
    deleteThisQuestion() {
      this.$store.commit('deleteOneQuestion', this.index)
      delete this
    }
  }
}
</script>

<style scoped>
#gap-filling-question .mask {
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

#gap-filling-question .confirm-deleting-question-box {
  z-index: 999;
  width: 300px;
  height: 120px;

  position: fixed;
  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;

  padding-top: 5px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  border-radius: 25px;
  background-color: white;
}

#gap-filling-question .confirm-deleting-question-box .btn-area {
  display: flex;
  justify-content: center;
  align-items: center;
}

#gap-filling-question .confirm-deleting-question-box .btn-area .cancel-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px grey;
  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#gap-filling-question .confirm-deleting-question-box .btn-area .cancel_active {
  border: solid 2px #a1a1a1;
  background-color: #a1a1a1;
  color: #fff;
}

#gap-filling-question .confirm-deleting-question-box .btn-area .confirm-btn {
  margin-left: 30px;
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px red;
  color: red;

  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#gap-filling-question .confirm-deleting-question-box .btn-area .confirm_active {
  border: solid 2px #bd0101;
  background-color: #bd0101;
  color: #fff;
}

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
