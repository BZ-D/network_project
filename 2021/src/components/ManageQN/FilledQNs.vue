<template>
  <div id="filled">
    <div class="search-results" v-show="!isBrowsingFilled">
      <!--   用于放置搜索到的问卷结果   -->
      <div class="qn-box" v-for="(qn, index) in searchResults">
        <div class="title">
          <h2>{{ qn.title }}</h2>
        </div>
        <div class="release-detail">
          <div class="releaser">
            发布者ID：{{ qn.uid }}
          </div>
          <div class="release-time">
            填写时间：{{ qn.time }}
          </div>
        </div>
        <div class="fill-btn-area">
          <div :class="{active:qn.mouseOnCheck}" class="fill-btn" @mouseover="qn.mouseOnCheck=true"
               @mouseleave="qn.mouseOnCheck=false" @click="gotoFilledContent(qn.uid, qn.qnID, qn.title)">
            查看填写情况
          </div>
        </div>
      </div>
    </div>

    <div class="filled-content" v-show="isBrowsingFilled">
      <!--   用于显示填写情况   -->
      <h2 class="qn-title">{{ filledQNTitle }}</h2>
      <div class="questions-area">
        <div class="question-box" v-for="(question, ind) in filledContent">
          <div class="answerselect" v-for="(questionSelect, ind2) in questionContent">
            <div class="qestion-answer" v-if="question.id===questionSelect.id">
          <h2 class="title">题目ID：{{ question.id }}
            ({{
              question.type === 'single' ? '单选题' : question.type === 'multiple' ? '多选题' : '填空题'
            }})</h2>
          <div class="title-show-area">
            题目名称：{{ questionSelect.qname}}
          </div>
          <div class="opt-show-area">
            {{ questionSelect.content }}
          </div>
          <div class="answer-show-area">
            您的答案：{{ question.answer }}
          </div>
            <div class="underline" v-if="question.type==='gap-fill'"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>

import {checkFilled} from "@/api/questionare";
import {getAnswers} from "@/api/answer";
import {getQuestions} from "@/api/question";

export default {
  name: "FilledQNs",
  data() {
    return {
      searchResults: [
        // {
        //   title: '关于大学生兼职状况的调查问卷',
        //   uid: 1,
        //   qnID: 1,
        //   uname: '丁炳智',
        //   time: '2021-5-31',
        //   mouseOnCheck: false
        // }
      ],
      isBrowsingFilled: false,  // 是否正在查看已填写问卷的填写情况
      filledQNTitle: '',
      questionContent: [
      ],
      filledContent: [
        // 元素为question对象
        // 20,
        // 1,
        // 3,
        // '学长',
        // '123456',
        // [0, 1, 1, 0, 0, 1]
      ]
    }
  },
  methods: {
    gotoFilledContent(uid, qnID, title) {
      this.isBrowsingFilled = true
      this.filledQNTitle = title
      getQuestions(qnID).then(res => {
        console.log(res)
        for (const question of res) {
          if (question.type === 'gap-fill') {
            this.questionContent.push({
              id: question.id,
              type: 'gap-fill',
              qname:question.questionTitle,
              content:'填空题无选项'
            })
          }
          if (question.type === 'single') {
            let singlec=''
            if(question.optionA!='无'){
              singlec=singlec+'A:'+question.optionA+'\n'
              if(question.optionB!='无'){
                singlec=singlec+'B:'+question.optionB+'\n'
                if(question.optionC!='无'){
                  singlec=singlec+'C:'+question.optionC+'\n'
                  if(question.optionD!='无'){
                    singlec=singlec+'D:'+question.optionD+'\n'
                    if(question.optionE!='无'){
                      singlec=singlec+'E:'+question.optionE+'\n'
                      if(question.optionF!='无'){
                        singlec=singlec+'F:'+question.optionF+'\n'
                      }
                    }
                  }
                }
              }
            }
            this.questionContent.push({
              id: question.id,
              type: 'single',
              qname:question.questionTitle,
              content:singlec
            })
          }
          if (question.type === 'multiple') {
            let singlec=''
            if(question.optionA!='无'){
              singlec=singlec+'A:'+question.optionA+'\n'
              if(question.optionB!='无'){
                singlec=singlec+'B:'+question.optionB+'\n'
                if(question.optionC!='无'){
                  singlec=singlec+'C:'+question.optionC+'\n'
                  if(question.optionD!='无'){
                    singlec=singlec+'D:'+question.optionD+'\n'
                    if(question.optionE!='无'){
                      singlec=singlec+'E:'+question.optionE+'\n'
                      if(question.optionF!='无'){
                        singlec=singlec+'F:'+question.optionF+'\n'
                      }
                    }
                  }
                }
              }
            }
            this.questionContent.push({
              id: question.id,
              type: 'multiple',
              qname:question.questionTitle,
              content:singlec
            })
          }
        }
      })
      getAnswers({
        qnId: qnID,
        uid: uid
      }).then(res => {
        console.log(res)
        for (const ans of res) {
          if (ans.gapAnswer === '无') {
            // 不是填空题
            const options = ['A', 'B', 'C', 'D', 'E', 'F']
            if (ans.singleAnswer === 0) {
              // 多选题
              let multiAns = ''
              const optionsAns = [
                ans.multiOne,
                ans.multiTwo,
                ans.multiThree,
                ans.multiFour,
                ans.multiFive,
                ans.multiSix
              ]
              for(const i in optionsAns) {
                if (optionsAns[i]) {
                  multiAns += options[i]
                }
              }
              this.filledContent.push({
                id: ans.questionId,
                answer: multiAns,
                type: 'multiple'
              })
            } else {
              // 单选题
              this.filledContent.push({
                answer: options[ans.singleAnswer - 1],
                type: 'single',
                id: ans.questionId
              })
            }
          } else {
            // 填空题
            this.filledContent.push({
              answer: ans.gapAnswer,
              type: 'gapFill',
              id: ans.questionId
            })
          }
        }
      })
    }
  },
  created() {
    const userID = localStorage.getItem("userId")
    checkFilled(userID).then(res => {
      console.log(res)
      for (const qn of res) {
        this.searchResults.push({
          title: qn.titleOfQn,
          uid: qn.createUserId,
          qnID: qn.id,
          time: qn.createTime.substr(0, 10),
          mouseOnCheck: false
        })
      }
    })
  }
}

</script>

<style scoped>

#filled .search-results .qn-box {
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

#filled {
  display: flex;
  align-items: center;
  justify-content: center;
}

#filled .search-results .qn-box .title {
  margin-bottom: 12px;
}

#filled .search-results .qn-box .release-detail {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 12px;
}

#filled .search-results .qn-box .release-detail .releaser {
  margin-right: 30px;
}

#filled .search-results .qn-box .fill-btn-area {
  display: flex;
  align-items: center;
  justify-content: center;
}

#filled .search-results .qn-box .fill-btn-area .fill-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px #3582e7;
  color: #3582e7;

  width: 120px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#filled .search-results .qn-box .fill-btn-area .active {
  border: solid 2px #3582e7;
  background-color: #3582e7;
  color: #fff;
}

#filled .filled-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#filled .filled-content .qn-title {
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

#filled .filled-content .questions-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#filled .filled-content .questions-area .question-box {
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

#filled .filled-content .questions-area .title {
  margin-bottom: 18px;
  font-size: 22px;
}

#filled .filled-content .questions-area .options-area li {
  margin-bottom: 5px;
  text-align: left;

  font-size: 18px;
}

#filled .filled-content .questions-area .options-area .selected {
  font-weight: bold;
  color: #4ab6e8;
}

#filled .filled-content .questions-area .gap-fill-area {
  font-size: 18px;
  font-weight: bold;
  color: #4ab6e8;
}

#filled .filled-content .questions-area .underline {
  width: 80%;
  border-bottom: solid 1px black;
}

</style>
