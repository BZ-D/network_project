<template>
  <div id="single-question">

    <div class="mask" v-show="isDeleting">
      <!-- 作用是突出显示确认是否删除题目窗口 -->
    </div>

    <div class="options-show-area">
      <div class="question-title-show-area">
        <h2>{{ index + 1 }}、(单选题) {{ $store.state.questions[index].title }} ({{ must }})</h2>
      </div>

      <!-- 显示选项的地方，每个选项最多为25字 -->
      <ol type="A" class="options-list">
        <li v-for="(item, i) in options" :key="i">
          <input type="text" :class="{beyond_limit: item.length>20}" class="option-add-input" placeholder="请在此处输入选项内容" v-model="options[i]"
                 @blur="updateVuexOptions">
          <img v-show="!mouseOnSub[i]" :src="require('@/assets/img/createQN/减号.png')"
               alt="" @mouseover="mouseOnSub.splice(i, 1, true)">
          <img v-show="mouseOnSub[i]" :src="require('@/assets/img/createQN/减号 (1).png')"
               @click="delOneOption(i); mouseOnSub.splice(i, 1, false)"
               alt="" @mouseleave="mouseOnSub.splice(i, 1, false)">
        </li>
      </ol>


      <span class="add-option-btn" v-if="options.length<max_num_of_options">
        <img v-show="!mouseOnAdd" :src="require('@/assets/img/createQN/加号.png')"
             alt="" @mouseover="mouseOnAdd=true">
        <img v-show="mouseOnAdd" :src="require('@/assets/img/createQN/加号 (1).png')"
             @click="addOneOption"
             alt="" @mouseleave="mouseOnAdd=false"><br>
        <span class="option-add-hint">点击按钮增加一个选项（最多添加6个）</span>
      </span>

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
  name: "Single",
  props: {
    index: Number, // 当前问题的下标
  },
  data() {
    // 最多六个选项
    return {
      max_num_of_options: 6,
      mouseOnAdd: false,
      mouseOnSub: [],  // 用于每个选项后面的减号图标
      mouseOnDelete: false,  // 用于删除题目按钮变化
      isDeleting: false,  // 用于删除题目的确认窗口
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

      // 及时更新store
      this.$store.commit('updateCertainQuestionOptions', {
        index: this.index,
        options: this.options
      })
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
      // 当选项输入框失焦时，先检查选项文字长度，符合限制后及时更新store.state中对应选择题的options属性
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
#single-question {
  margin: 0 auto;
}

#single-question .mask {
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

#single-question .confirm-deleting-question-box {
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

#single-question .confirm-deleting-question-box .btn-area {
  display: flex;
  justify-content: center;
  align-items: center;
}

#single-question .confirm-deleting-question-box .btn-area .cancel-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px grey;
  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#single-question .confirm-deleting-question-box .btn-area .cancel_active {
  border: solid 2px #a1a1a1;
  background-color: #a1a1a1;
  color: #fff;
}

#single-question .confirm-deleting-question-box .btn-area .confirm-btn {
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

#single-question .confirm-deleting-question-box .btn-area .confirm_active {
  border: solid 2px #bd0101;
  background-color: #bd0101;
  color: #fff;
}

#single-question .options-show-area {
  display: flex;
  flex-direction: column;
  /*align-items: center;*/
  /*justify-content: center;*/

  width: 650px;
  /*height: 350px;*/
  border-radius: 25px;
  background: #fff;

  margin: 0 auto 20px;
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

#single-question .options-show-area .options-list .beyond_limit {
  color: red;
}

#single-question .options-show-area .options-list li {
  margin-bottom: 5px;
}

#single-question .options-show-area .options-list li input {
  border: 0;
  width: 420px;
  height: 40px;
  text-align: center;
  border-bottom: 1px solid #000;
  font-size: 20px;
  outline: none;
}

#single-question .options-show-area .delete-question-btn {
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

#single-question .options-show-area .active {
  color: #fff;
  background-color: #bd0101;
  border: solid 2px #bd0101;
}

</style>
