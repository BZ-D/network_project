<template>
  <div id="create-detail">

    <div class="mask" v-show="$store.state.isAdding || isDiscardingQN || isReleasingQN || isDraftingQN">
      <!-- 作用是突出显示添加题目窗口，让其他元素变为灰色不可选，当正在添加题目、正在丢弃问卷、发布问卷、保存草稿时 -->
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
      <!-- 在所有题目的最下方显示这三个按钮 -->
      <span :class="{active: isCancelQNBtnActive}" class="cancel-questionnaire-btn" @click="isDiscardingQN=true"
            @mouseover="isCancelQNBtnActive=true" @mouseleave="isCancelQNBtnActive=false">丢 弃 问 卷</span>
      <span :class="{active: isDraftQNBtnActive}" class="draft-questionnaire-btn" @click="isDraftingQN=true"
            @mouseover="isDraftQNBtnActive=true" @mouseleave="isDraftQNBtnActive=false">存 为 草 稿</span>
      <span :class="{active: isConfirmQNBtnActive}" class="confirm-questionnaire-btn" @click="isReleasingQN=true"
            @mouseover="isConfirmQNBtnActive=true" @mouseleave="isConfirmQNBtnActive=false">发 布 问 卷</span>
    </div>

    <add_question_box v-show="$store.state.isAdding"></add_question_box>

    <div class="confirm-deleting-this-qn" v-show="isDiscardingQN">
      <!-- 确认是否删除此问卷窗口 -->
      <span class="hint">确定要删除这张问卷吗？</span><br>
      <div class="btn-area">
        <span :class="{cancel_active: mouseOnCancelDiscard}" class="cancel-btn" @click="isDiscardingQN=false"
              @mouseover="mouseOnCancelDiscard=true" @mouseleave="mouseOnCancelDiscard=false">取 消</span>
        <span :class="{confirm_active: mouseOnConfirmDiscard}" class="confirm-btn" @click="discardThisQN"
              @mouseover="mouseOnConfirmDiscard=true" @mouseleave="mouseOnConfirmDiscard=false">确 定</span>
      </div>
    </div>

    <div class="confirm-draft-this-qn" v-show="isDraftingQN">
      <!-- 确认是否保存草稿窗口 -->
      <span class="hint">确定要保存为草稿吗？</span><br>
      <div class="btn-area">
        <span :class="{cancel_active: mouseOnCancelDraft}" class="cancel-btn" @click="isDraftingQN=false"
              @mouseover="mouseOnCancelDraft=true" @mouseleave="mouseOnCancelDraft=false">取 消</span>
        <span :class="{confirm_active: mouseOnConfirmDraft}" class="confirm-btn" @click="draftThisQN"
              @mouseover="mouseOnConfirmDraft=true" @mouseleave="mouseOnConfirmDraft=false">确 定</span>
      </div>
    </div>

    <div class="confirm-release-this-qn" v-show="isReleasingQN">
      <!-- 确认是否发布此问卷窗口 -->
      <span class="hint">确定要发布这张问卷吗？</span><br>
      <div class="btn-area">
        <span :class="{cancel_active: mouseOnCancelRelease}" class="cancel-btn" @click="isReleasingQN=false"
              @mouseover="mouseOnCancelRelease=true" @mouseleave="mouseOnCancelRelease=false">取 消</span>
        <span :class="{confirm_active: mouseOnConfirmRelease}" class="confirm-btn" @click="releaseThisQN"
              @mouseover="mouseOnConfirmRelease=true" @mouseleave="mouseOnConfirmRelease=false">确 定</span>
      </div>
    </div>
  </div>
</template>

<script>
// 刷新页面时弹出“系统可能不会保存您的更改”
import router from "@/router";

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
      isCancelQNBtnActive: false, // 用于 丢弃问卷 按钮变化
      isDraftQNBtnActive: false,  // 用于 存为草稿 按钮变化
      isConfirmQNBtnActive: false,// 用于 发布问卷 按钮变化
      isDiscardingQN: false,  // 用于点击 丢弃问卷 按钮后弹出的确认窗口突出显示
      isReleasingQN: false,   // 用于点击 发布问卷 按钮后弹出的确认窗口突出显示
      isDraftingQN: false,    // 用于点击 存为草稿 按钮后弹出的确认窗口突出显示

      mouseOnCancelDiscard: false,   // 丢弃问卷窗口取消按钮变化
      mouseOnConfirmDiscard: false,   // 丢弃问卷窗口确认按钮变化

      mouseOnCancelDraft: false,// 保存草稿窗口取消按钮变化
      mouseOnConfirmDraft: false,// 保存草稿窗口确定按钮变化

      mouseOnCancelRelease: false,// 发布问卷窗口取消按钮变化
      mouseOnConfirmRelease: false,// 丢弃问卷窗口确定按钮变化
    }
  },
  methods: {
    discardThisQN() {
      // 删除所有题目，删除问卷标题，回到输入问卷标题页面main
      this.$store.commit('deleteQN_when_creating')
      this.$router.push('/main')
    },
    releaseThisQN() {
      // 先检查每一道题，标准为：
      // 单选：选项个数大于0且小于等于6，每个创建的选项内容长度应大于0字且小于等于25字
      // 多选：同单选
      // 填空：无需检查
      for(let i = 0; i < this.$store.state.questions.length; ++i) {
        const question = this.$store.state.questions[i]
        //  question格式: 对象
        // 对象内容：title, type, must, options(如果是选择题, options要有元素)
        if (question.type === 'single' || question.type === 'multiple') {
          if (question.options.length === 0) {
            // 某个选择题没有选项
            window.alert('您的第' + (i + 1) + '道题没有选项，请进行处理！')
            this.isReleasingQN = false
            return
          }
          for(let j = 0; j < question.options.length; ++j) {
            const option = question.options[j]
            // 某个选项长度不符合要求
            if (option.length === 0) {
              window.alert('您的第' + (i + 1) + '道题的第' + (j + 1) + '个选项为空，请进行处理！')
              this.isReleasingQN = false
              return
            }
            if (option.length > 25) {
              window.alert('您的第' + (i + 1) + '道题的第' + (j + 1) + '个选项超过了25个字，请进行处理！')
              this.isReleasingQN = false
              return
            }
          }
        }
      }

      // TODO: 检查结束，每个问题都符合要求后，下面要把问卷传到数据库
    },
    draftThisQN() {

    }
  }

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

#create-detail .confirm-deleting-this-qn,
#create-detail .confirm-release-this-qn,
#create-detail .confirm-draft-this-qn {
  /* 是否丢弃、存草稿、发布问卷的弹出式确认窗口 */
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

#create-detail .confirm-deleting-this-qn .btn-area,
#create-detail .confirm-release-this-qn .btn-area,
#create-detail .confirm-draft-this-qn .btn-area {
  display: flex;
  justify-content: center;
  align-items: center;
}

#create-detail .confirm-deleting-this-qn .btn-area .cancel-btn,
#create-detail .confirm-release-this-qn .btn-area .cancel-btn,
#create-detail .confirm-draft-this-qn .btn-area .cancel-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px grey;
  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#create-detail .confirm-deleting-this-qn .btn-area .cancel_active,
#create-detail .confirm-release-this-qn .btn-area .cancel_active,
#create-detail .confirm-draft-this-qn .btn-area .cancel_active {
  border: solid 2px #a1a1a1;
  background-color: #a1a1a1;
  color: #fff;
}

#create-detail .confirm-deleting-this-qn .btn-area .confirm-btn {
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

#create-detail .confirm-deleting-this-qn .btn-area .confirm_active {
  border: solid 2px #bd0101;
  background-color: #bd0101;
  color: #fff;
}

#create-detail .confirm-draft-this-qn .btn-area .confirm-btn {
  margin-left: 30px;
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px #f17e22;
  color: #f17e22;

  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#create-detail .confirm-draft-this-qn .btn-area .confirm_active {
  border: solid 2px #f17e22;
  background-color: #f17e22;
  color: #fff;
}

#create-detail .confirm-release-this-qn .btn-area .confirm-btn {
  margin-left: 30px;
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px #3582e7;
  color: #3582e7;

  width: 70px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#create-detail .confirm-release-this-qn .btn-area .confirm_active {
  border: solid 2px #3582e7;
  background-color: #3582e7;
  color: #fff;
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

  margin-bottom: 20px;
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
