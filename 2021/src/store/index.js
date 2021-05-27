import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    maxOfQuestions: 20,  // 题目最大数量
    titleOfQN: '',  // 现在正在创建问卷的题目
    isAdding: false, // 创建问卷页面是否正在添加题目？（true：显示题型选择框）
    numOfQuestions: 0,  //  记录题目数量，上限为20

    questionAddingInfo: {
      title: '',  // 目前正在添加的题目名称
      type: '',  // 目前正在添加的题型
      must: '',  // 目前正在添加的题目必答与否

      options: [
        // 选择题的选项，最多6项
      ]
    },


    questions: [
      // 已添加的题目，里边存对象
      // 对象内容：title, type, must, options(如果是选择题的话)
    ]
  },
  mutations: {
    fillTitle(state, QN_title) {
      state.titleOfQN = QN_title
    },
    toAdding(state) {
      state.isAdding = true
    },
    addingOneQuestion(state, info) {
      // 正在添加一道题目
      state.isAdding = false
      state.numOfQuestions++
      state.questionAddingInfo.title = info.title
      state.questionAddingInfo.type = info.type
      state.questionAddingInfo.must = info.must
      state.questions.push({
        title: info.title,  // 目前正在添加的题目名称
        type: info.type,  // 目前正在添加的题型
        must: info.must,  // 目前正在添加的题目必答与否
        options: []
      })
    },
    cancelAdding(state) {
      // 取消添加题目窗口
      state.isAdding = false
    },
    deleteOneQuestion(state) {
      state.numOfQuestions--
    },
    updateCertainQuestionOptions(state, payload) {
      // 当某一道题的选项文本框失焦时，及时更新到store里

      // 先清空
      state.questions[payload.index].options.length = 0

      for (const option of payload.options) {
        state.questions[payload.index].options.push(option)
      }
    }
  },
  actions: {},
  modules: {}
})
