<template>
  <div id="released">

    <div class="search-results" v-show="!isBrowsingReleased">
      <!--   用于放置搜索到的问卷结果   -->
      <div class="qn-box" v-for="(qn, index) in searchResults">
        <div class="title">
          <h2>{{ qn.title }}</h2>
        </div>
        <div class="release-detail">
          <div class="release-time">
            发布时间：{{ qn.time }}
          </div>
        </div>
        <div class="fill-btn-area">
          <div :class="{active:qn.mouseOnCheck}" class="fill-btn" @mouseover="qn.mouseOnCheck=true"
               @mouseleave="qn.mouseOnCheck=false" @click="exportResults(qn.uid, qn.qnID, qn.title)">
            导出问卷填写情况
          </div>
        </div>
      </div>
    </div>

    <div class="qn-content" v-show="isBrowsingReleased">
      <!--   用于显示填写情况   -->
      <h2 class="qn-title">{{ releasedQNTitle }}</h2>
      <div class="questions-area">
        <div class="question-box" v-for="(question, ind) in questions">
          <h2 class="title">{{ ind + 1 }}、{{ question.title }}
            ({{ question.type === 'single' ? '单选题' : question.type === 'multiple' ? '多选题' : '填空题' }}、{{question.must === 'must' ? '必答' : '选答'}})</h2>

          <div class="options-area" v-if="question.type!=='gap-fill'">
            <ol class="option" type="A">
              <li v-for="(option) in question.options">
                {{ option }}
              </li>
            </ol>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {checkFilled, checkReleased} from "@/api/questionare";

export default {
  name: "publishedQNs",
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
    }
  },
  methods: {
    exportResults(uid, qnID, title) {
      // 导出问卷填写情况
    }
  },
  created() {
    const userID = localStorage.getItem("userId")
    checkReleased(userID).then(res => {
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

// TODO: 显示当前用户发布过的所有问卷，这些问卷都可以查看，点击查看问卷后，页面显示checkReleasedQN组件
</script>

<style scoped>

#released .search-results .qn-box {
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

#released {
  display: flex;
  align-items: center;
  justify-content: center;
}

#released .search-results .qn-box .title {
  margin-bottom: 12px;
}

#released .search-results .qn-box .release-detail {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 12px;
}

#released .search-results .qn-box .release-detail .releaser {
  margin-right: 30px;
}

#released .search-results .qn-box .fill-btn-area {
  display: flex;
  align-items: center;
  justify-content: center;
}

#released .search-results .qn-box .fill-btn-area .fill-btn {
  display: flex;
  align-items: center;
  justify-content: center;

  border: solid 2px #3582e7;
  color: #3582e7;

  width: 160px;
  height: 30px;
  background-color: #fff;

  cursor: pointer;
}

#released .search-results .qn-box .fill-btn-area .active {
  border: solid 2px #3582e7;
  background-color: #3582e7;
  color: #fff;
}

#released .qn-content .questions-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#released .qn-content .questions-area .question-box {
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

#released .qn-content .qn-title {
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

#released .qn-content .questions-area .title {
  font-size: 22px;
  margin-bottom: 18px;
}

#released .qn-content .questions-area .options-area li {
  margin-bottom: 5px;
  text-align: left;

  font-size: 18px;
}


</style>
