<template>
  <div id="choose-type-box" v-show="ifChooseType">
    <!-- 选择题型的盒子，模拟弹窗，置于绝对位置屏幕正中间 -->
    <span class="question-name">
      <span class="question-name-fill-hint">题目名称</span><br>
      <input type="text" v-model="questionBasicInfo.title" class="question-name-fill-input"
             placeholder="请在此处输入问卷标题，最多25个字">
      <span :class="{beyond_limit: questionBasicInfo.title.length>25 || questionBasicInfo.title.length===0}"
            class="title-length-count">  {{ questionBasicInfo.title.length }}/25</span>
    </span><br>
    <span class="type-list">
        <span class="question-type-hint">题型：</span>
        <span class="question-type-choose">
          <span class="single-opt">
            <input type="radio" name="type" value="single" v-model="questionBasicInfo.type">
            <span class="type">单选题</span>
          </span>
          <span class="multi-opt">
            <input type="radio" name="type" value="multiple" v-model="questionBasicInfo.type">
            <span class="type">多选题</span>
          </span>
          <span class="gapfill-opt">
            <input type="radio" name="type" value="gap-fill" v-model="questionBasicInfo.type">
            <span class="type">填空题</span>
          </span>
        </span>
      </span><br>
    <span class="answer-required">
        <span class="answer-required-hint">是否必答：</span>
        <span class="required-choose-area">
          <span>
            <input type="radio" name="required" value="must" v-model="questionBasicInfo.must">
            <span class="required-type">必答</span>
          </span>
          <span>
            <input type="radio" name="required" value="optional" v-model="questionBasicInfo.must">
            <span class="required-type">选答</span>
          </span>
        </span>
      </span><br>

    <span :class="{add_active: addOneBtnFocus}" @click="addQuestion" class="add-one-btn"
          @mouseover="addOneBtnFocus=true" @mouseleave="addOneBtnFocus=false">
        添 加 题 目
      </span>
  </div>
</template>

<script>
export default {
  name: "AddQuestionBox",
  data() {
    return {
      addOneBtnFocus: false,  // 用于选择题型窗口“添加题目”按钮的变化
      ifChooseType: true,
      questionBasicInfo: {
        title: '',  // 题干
        type: '',   // 题目类型
        must: '',  // 是否必答
      }
    }
  },
  methods: {
    addQuestion() {
      if (this.questionBasicInfo.title.length === 0) {
        window.alert('请输入题目名称！')
        return
      } else if (this.questionBasicInfo.title.length > 25) {
        window.alert('题目名称最多为25个字！')
        return
      } else if (this.questionBasicInfo.type.length === 0) {
        window.alert('请选择一个题目类型！')
        return
      } else if (this.questionBasicInfo.must.length === 0) {
        window.alert('请选择该题是否为必答！')
        return
      }

      this.$store.commit('addingOneQuestion', this.questionBasicInfo)
    }
  }
}
</script>

<style scoped>
#choose-type-box {
  /* 该组件永远处于屏幕正中间 */
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;


  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  width: 650px;
  border-radius: 25px;
  height: 300px;
  background: rgba(0, 0, 0, .6);
}

#choose-type-box * {
  display: flex;
  align-items: center;
  margin-right: auto;
}

#choose-type-box .question-name {
  /*display: unset;*/

  width: 100%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

}

#choose-type-box .question-name span {
  text-align: center;

  margin: 0 auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}

#choose-type-box .type-list, .answer-required {
  /* 盒子里的题目、题型、是否必答三个span左对齐，左边留30%的空隙（相对于父元素） */
  margin-left: 30%;
}

#choose-type-box .question-name .question-name-fill-hint,
.type-list .question-type-hint,
.answer-required .answer-required-hint {
  font-size: 20px;
  color: #fff;
  text-shadow: 1px 1px 1px #000;
  cursor: default;
}

#choose-type-box .question-name .question-name-fill-hint {
  font-size: 25px;
}

#choose-type-box .question-name .question-name-fill-input {
  /*题目填写框样式调整，留底下白线*/
  border: 0;
  border-bottom: 1px solid #fff;
  background-color: #ffffff00;
  color: #fff;
  font-size: 15px;

  width: 60%;
  height: 30px;

  margin-right: unset;
  text-align: center;
  font-weight: lighter;
}

#choose-type-box .type-list .question-type-choose span {
  /*让选项之间有点距离*/
  margin-right: 10px;
}

#choose-type-box .answer-required .required-choose-area span {
  /*让选项之间有点距离*/
  margin-right: 10px;
}

#choose-type-box .type-list .question-type-choose input[type="radio"],
.answer-required input[type="radio"] {
  /*把单选项前面的圆圈样式调整*/
  width: 20px;
  height: 20px;
  opacity: .5;
  cursor: pointer;
  border-radius: 50%;
  border: 1px solid #999;
}

#choose-type-box .type-list .question-type-choose .type,
.answer-required .required-type {
  font-size: 15px;
  color: #fff;
  text-shadow: 1px 1px 1px #000;
  cursor: default;
}

#choose-type-box .add-one-btn {
  /* 鼠标没有移到 添加题目 按钮上 */
  color: #fff;
  text-shadow: #000000 1px 1px 1px;

  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;

  cursor: pointer;

  margin: 0 auto;

  width: 145px;
  height: 38px;
  border-radius: 25px;
  background: linear-gradient(120deg, #9c7ae3 0%, #7eec7d 100%);
  font-size: 17px;
}

#choose-type-box .add_active {
  /* 鼠标移到 添加题目 按钮上 */
  background: linear-gradient(120deg, #9876de 0%, #5acd57 100%);
}

.beyond_limit {
  color: #b60505;
}
</style>
