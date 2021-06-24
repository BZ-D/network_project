<template>
  <div id="search">

    <div v-show="!isFillingQN">
      <div class="search-box">
      <span class="search-area">
        <input :class="{beyond_limit: beyond_limit}" type="text" :placeholder="placeholder" v-model="searchContent">
      </span>
        <span class="search-type">
        <select name="search-type" id="" @change="changeSearchType($event)" class="options">
          <option value="name-of-qn">按问卷名称</option>
          <option value="name-of-user">按发布人id</option>
        </select>
      </span>
        <span class="search-icon">
        <img v-show="!mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜.png')"
             @mouseover="mouseOnSearch=true" alt="">
        <img v-show="mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜mouseon.png')" @click="searchQN"
             @mouseleave="mouseOnSearch=false" alt="">
      </span>
      </div>

      <div class="search-results">
        <!--   用于放置搜索到的问卷结果   -->
        <div class="qn-box" v-for="(qn, index) in searchResults">
          <div class="title">
            <h2>{{ qn.titleOfQn }}</h2>
          </div>
          <div class="release-detail">
            <div class="releaser">
              发布人ID：{{ qn.createUserId }}
            </div>
            <div class="release-time">
              发布时间：{{ qn.createTime }}
            </div>
          </div>
          <div class="fill-btn-area">
            <div :class="{active:qn.mouseOnFill}" class="fill-btn" @mouseover="qn.mouseOnFill=true"
                 @mouseleave="qn.mouseOnFill=false" @click="gotoFillQN(qn.qnId, qn.titleOfQn)">
              填写问卷
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="fill-area" v-show="isFillingQN">
      <h2 class="qn-title">{{ fillingQNTitle }}</h2>
      <div class="questions-area">
        <div class="question-box" v-for="(question, ind) in questions">
          <h2 class="title">{{ ind + 1 }}、{{ question.title }}
            ({{
              question.type === 'single' ? '单选题' : question.type === 'multiple' ? '多选题' : '填空题'
            }}、{{ question.must === 'must' ? '必答' : '选答' }})
          </h2>

          <div class="single-options-area" v-if="question.type==='single'">
            <div :class="{active: answersArray[ind] === i + 1}" class="option" v-for="(option, i) in question.options"
                 @click="updateSingle(ind, i)">
              {{ ['A', 'B', 'C', 'D', 'E', 'F'][i] }}. {{ option }}
            </div>
          </div>

          <div class="multiple-options-area" v-if="question.type==='multiple'">
            <div :class="{active: answersArray[ind][i] === 1}" class="option" v-for="(option, i) in question.options"
                 @click="updateMultiple(ind, i)">
              {{ ['A', 'B', 'C', 'D', 'E', 'F'][i] }}. {{ option }}
            </div>
          </div>

          <div class="gap-fill-input" v-if="question.type==='gap-fill'">
            <input type="text" v-model.trim="answersArray[ind]" placeholder="请在此输入您的答案">
          </div>
        </div>
      </div>

      <div class="cancel-and-confirm-area">
        <!-- 在所有题目的最下方显示这三个按钮 -->
        <span :class="{active: isCancelQNBtnActive}" class="cancel-questionnaire-btn"
              @mouseover="isCancelQNBtnActive=true" @mouseleave="isCancelQNBtnActive=false" @click="cancelFilling">放 弃 填 写</span>
        <span :class="{active: isConfirmQNBtnActive}" class="confirm-questionnaire-btn"
              @mouseover="isConfirmQNBtnActive=true" @mouseleave="isConfirmQNBtnActive=false" @click="confirmFilling">填 写 完 成</span>
      </div>
    </div>


  </div>
</template>

<script>

import {searchByTitle, searchByUser} from "@/api/questionare";
import {getQuestions} from "@/api/question";

export default {
  name: "SearchQN",
  computed: {
    beyond_limit() {
      return this.searchContent.length > 25
    },
  },
  data() {
    return {
      mouseOnSearch: false,
      isCancelQNBtnActive: false,
      isConfirmQNBtnActive: false,
      placeholder: '请在此输入问卷名称',
      searchContent: '',
      fillingQNId: 0,
      searchResults: [
        // 存放搜索到的结果
      ],
      isFillingQN: false,
      fillingQNTitle: '',
      questions: [
        // 用于存放选择填写的问卷包含的问题
      ],
      answersArray: [],
      answers: [
        // 内存对象，最终传到后端的数组
        /*
        {
          answerUserId: 1,
          answerQnId: 1,
          questionId: 1,
          gapAnswer: '',
          singleAnswer: 0,
          multiAnswer: [0,0,0,0,0,0]
        }
         */]
    }
  },
  methods: {
    changeSearchType(event) {
      this.placeholder = event.target.value === 'name-of-qn' ? '请在此输入问卷名称' : '请在此输入发布人id'
    },
    searchQN() {
      // 点击放大镜图标后搜索
      // 先检查输入框内文本是否符合要求
      if (this.searchContent.length === 0) {
        window.alert('请输入内容后再搜索！')
        return
      }
      if (this.beyond_limit) {
        window.alert('输入文字的长度不能超过25个字，请重新输入！')
        return
      }
      // TODO: 在输入框文本合格后，请求后端数据库相关的问卷，并显示到页面上


      if (this.placeholder === '请在此输入问卷名称') {
        // 按问卷名称搜索
        searchByTitle(this.searchContent).then(res => {
          console.log(res)
          if (res.length === 0) {
            window.alert('没有查找到相关问卷！')
            return
          }
          // res是Array类型，元素是问卷结果VO，内容有：
          // createTime: "2021-06-03T08:00:00.000+00:00"
          // createUserId: int
          // id: int 问卷id
          // isDraft: bool
          // numOfQuestions: int
          // titleOfQn: String
          for (const qn of res) {
            if (!qn.isDraft) {
              this.searchResults.push({
                createTime: qn.createTime.substr(0, 10),
                createUserId: qn.createUserId,
                qnId: qn.id,
                numOfQuestions: qn.numOfQuestions,
                titleOfQn: qn.titleOfQn,
                mouseOnFill: false
              })
            }
          }
        })
      } else {
        // 按用户id搜索
        searchByUser(parseInt(this.searchContent)).then(res => {
          console.log(res)
          if (res.length === 0) {
            window.alert('该用户没有发布问卷！')
            return
          }

          // res是Array类型，元素是问卷结果VO，内容有：
          // createTime: "2021-06-03T08:00:00.000+00:00"
          // createUserId: int
          // id: int 问卷id
          // isDraft: bool
          // numOfQuestions: int
          // titleOfQn: String
          for (const qn of res) {
            if (!qn.isDraft) {
              this.searchResults.push({
                createTime: qn.createTime.substr(0, 10),
                createUserId: qn.createUserId,
                qnId: qn.id,
                numOfQuestions: qn.numOfQuestions,
                titleOfQn: qn.titleOfQn,
                mouseOnFill: false
              })
            }
          }
        })
      }
    },
    gotoFillQN(qnID, title) {
      getQuestions(qnID).then(res => {
        console.log(res)
        for (const question of res) {
          let options = []
          if(question.optionA !== "无")
            options.push(question.optionA)
          if(question.optionB !== "无")
            options.push(question.optionB)
          if(question.optionC !== "无")
            options.push(question.optionC)
          if(question.optionD !== "无")
            options.push(question.optionD)
          if(question.optionE !== "无")
            options.push(question.optionE)
          if(question.optionF !== "无")
            options.push(question.optionF)

          this.questions.push({
            id: question.id,
            type: question.type,
            must: question.isMust,
            options: options,
            title: question.questionTitle
          })

          // 初始化answersArray数组
          switch (question.type) {
            case "single":
              this.answersArray.push(0)
              break
            case "multiple":
              this.answersArray.push([0, 0, 0, 0, 0, 0])
              break
            case "gap-fill":
              this.answersArray.push('')
              break
          }
        }
      })

      this.isFillingQN = true
      this.fillingQNTitle = title
      this.fillingQNId = qnID
    },
    updateSingle(ind, option) {
      // 单选题更新答案
      this.answersArray.splice(ind, 1, option + 1)
    },
    updateMultiple(ind, option) {
      // 多选题更新答案
      // ind: 当前题目在questions数组的下标
      // option: 当前选中的选项在options数组中的下标，数字0~5
      if (this.answersArray[ind][option] === 1) {
        this.answersArray[ind].splice(option, 1, 0)
      } else {
        this.answersArray[ind].splice(option, 1, 1)
      }

    },
    cancelFilling() {
      const confirm = window.confirm('确定丢弃填写内容吗？')
      if (confirm) {
        location.reload()
      }
    },
    confirmFilling() {
      const confirm = window.confirm('确定提交问卷吗？')
      if (confirm) {
        // 检查每道题的答案是否规范
        for (let i = 0; i < this.answersArray.length; ++i) {
          // 先检查是否有必答题漏答
          if (this.questions[i].must === 'must') {
            if ((this.questions[i].type === 'single' && this.answersArray[i] === 0) ||
                (this.questions[i].type === 'gap-fill' && this.answersArray[i] === '')) {
              window.alert('您的第' + (i + 1) + '道题尚未作答！')
              return
            }
            if (this.questions[i].type === 'multiple') {
              let select = false
              for (const multiAnswer of this.answersArray[i]) {
                if (multiAnswer === 1) {
                  select = true
                  break
                }
              }
              if (!select) {
                window.alert('您的第' + (i + 1) + '道必答题尚未作答！')
                return
              }
            }
          }
          if (this.questions[i].type === 'gap-fill') {
            if (this.answersArray[i].length > 25) {
              window.alert('您的第' + (i + 1) + '道填空题内容超过了25个字！')
            }
          }


        }
        // 把answerArray里的内容移到answers数组中
        for (let i = 0; i < this.answersArray.length; ++i) {
          // answers: [
          //   // 内存对象，最终传到后端的数组
          //   /*
          //   {
          //     answerUserId: 1,
          //     answerQnId: 1,
          //     questionId: 1,
          //     gapAnswer: '',
          //     singleAnswer: 0,
          //     multiAnswer: [0,0,0,0,0,0]
          //   }
          //    */]
          if (this.questions[i].type === 'gap-fill') {
            this.answers.push({
              answerUserId: this.$store.state.userID,
              answerQnId: this.fillingQNId,
              questionId: this.questions[i].id,
              gapAnswer: this.answersArray[i],
              singleAnswer: 0,
              multiAnswer: [0, 0, 0, 0, 0, 0]
            })
          } else if (this.questions[i].type === 'single') {
            this.answers.push({
              answerUserId: this.$store.state.userID,
              answerQnId: this.fillingQNId,
              questionId: this.questions[i].id,
              gapAnswer: '无',
              singleAnswer: this.answersArray[i],
              multiAnswer: [0, 0, 0, 0, 0, 0]
            })
          } else if (this.questions[i].type === 'multiple') {
            this.answers.push({
              answerUserId: this.$store.state.userID,
              answerQnId: this.fillingQNId,
              questionId: this.questions[i].id,
              gapAnswer: '无',
              singleAnswer: 0,
              multiAnswer: this.answersArray[i]
            })
          }

        }

      }
    }
  },
}
</script>

<style scoped>

#search {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#search .search-box {
  width: 700px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;

  margin-bottom: 20px;

  border-radius: 25px;
  background-color: #fff;
}

#search .search-box .search-area {
  width: 480px;
  height: 35px;
}

#search .search-box .search-area .beyond_limit {
  color: red;
}

#search .search-box .search-area input {
  width: 100%;
  height: 35px;

  border: 0;
  border-bottom: 1px solid #000;
  outline: none;

  text-align: center;
  font-size: 18px;
}

#search .search-box .search-type {
  width: 100px;
  height: 25px;
  margin-left: 10px;

}

#search .search-box .search-type .options {
  width: 100%;
  height: 100%;
  font-size: 13px;

  border: 0;
  border-right: 1px solid #9c9c9c;
  outline: none;
}

#search .search-box .search-icon {
  width: 35px;
  height: 35px;
  cursor: pointer;

  margin-left: 10px;
}

#search .search-box .search-icon img {
  width: 100%;
  height: 100%;
}

#search .search-results .qn-box {
  width: 700px;
  height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  margin-bottom: 15px;

  border-radius: 25px;
  background-color: #fff;
}

#search .search-results .qn-box .title {
  margin-bottom: 12px;
}

#search .search-results .qn-box .release-detail {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 12px;
}

#search .search-results .qn-box .release-detail .releaser {
  margin-right: 30px;
}

#search .search-results .qn-box .fill-btn-area {
  display: flex;
  align-items: center;
  justify-content: center;
}

#search .search-results .qn-box .fill-btn-area .fill-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px #3582e7;
  color: #3582e7;

  width: 80px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#search .search-results .qn-box .fill-btn-area .active {
  border: solid 2px #3582e7;
  background-color: #3582e7;
  color: #fff;
}

#search .fill-area {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#search .fill-area .questions-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#search .fill-area .qn-title {
  width: 600px;
  height: 50px;
  border-radius: 25px;
  background: rgba(0, 0, 0, 0.52);

  display: flex;
  align-items: center;
  justify-content: center;

  color: #ffffff;
  font-family: "STZhongsong", "STHeiti", serif;
  text-shadow: 1px 1px 1px #000;

  margin-bottom: 20px;
}

#search .fill-area .questions-area .question-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  width: 600px;
  background: white;
  margin-bottom: 20px;
  border-radius: 10px;
  padding-top: 20px;
  padding-bottom: 20px;

  box-shadow: 2px 2px 2px #c7c7c7;
}

#search .fill-area .questions-area .question-box .title {
  margin-bottom: 18px;
  font-size: 22px;
}

#search .fill-area .questions-area .question-box .single-options-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#search .fill-area .questions-area .question-box .single-options-area .option {
  font-size: 18px;
  margin-bottom: 5px;
  cursor: pointer;

  width: 300px;
  text-align: left;
  padding-left: 20px;
}

#search .fill-area .questions-area .question-box .multiple-options-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

}

#search .fill-area .questions-area .question-box .multiple-options-area .option {
  font-size: 18px;
  margin-bottom: 5px;
  cursor: pointer;

  width: 300px;
  text-align: left;
  padding-left: 20px;
}

#search .fill-area .questions-area .question-box .gap-fill-input input {
  width: 400px;
  height: 40px;
  border: none;
  border-bottom: solid 1px #000;
  outline: none;
  text-align: center;

  font-size: 19px;
}

#search .fill-area .questions-area .question-box .active {
  background: rgba(0, 0, 0, 0.18);
  border-radius: 20px;
  color: black;
}

#search .fill-area .cancel-and-confirm-area {
  width: 800px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;

  margin-bottom: 20px;
}

#search .fill-area .cancel-and-confirm-area span {
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

#search .fill-area .cancel-and-confirm-area .active {
  background: linear-gradient(120deg, #809ce3 0%, #8074ec 100%);
}

#search .fill-area .cancel-and-confirm-area .confirm-questionnaire-btn {
  margin-left: 40px;
}

</style>
