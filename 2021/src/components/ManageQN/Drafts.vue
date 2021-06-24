<template>
  <div id="draft">
    <div class="search-results">
      <!--   用于放置搜索到的问卷结果   -->
      <div class="qn-box" v-for="(qn, index) in searchResults">
        <div class="title">
          <h2>{{ qn.title }}</h2>
        </div>
        <div class="release-detail">
          <div class="release-time">
            保存时间：{{ qn.time }}
          </div>
        </div>
        <div class="fill-btn-area">
          <div :class="{active:qn.mouseOnCheck}" class="fill-btn" @mouseover="qn.mouseOnCheck=true"
               @mouseleave="qn.mouseOnCheck=false" @click="gotoDetail(qn.qnID, qn.title)">
            继续编辑
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {checkDraft, checkFilled} from "@/api/questionare";

export default {
  name: "Drafts",
  data() {
    return {
      searchResults: [
        // {
        //   title: '关于大学生兼职状况的调查问卷',
        //   qnID: 1,
        //   uname: '丁炳智',
        //   time: '2021-5-31',
        //   mouseOnCheck: false
        // }
      ],
    }
  },
  methods: {
    gotoDetail(qnID, title) {
      this.$store.commit('gotoDetailFromDrafts', title)
      this.$router.push('/main/detail')
    }
  },
  created() {
    const userID = localStorage.getItem("userId")
    checkDraft(userID).then(res => {
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


// TODO: 显示当前用户保存的所有草稿，用户可以点击继续编辑，此时跳转到create_detail界面，也可以点击删除草稿，从数据库中移除
</script>

<style scoped>

#draft .search-results .qn-box {
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

#draft {
  display: flex;
  align-items: center;
  justify-content: center;
}

#draft .search-results .qn-box .title {
  margin-bottom: 12px;
}

#draft .search-results .qn-box .release-detail {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 12px;
}


#draft .search-results .qn-box .fill-btn-area {
  display: flex;
  align-items: center;
  justify-content: center;
}

#draft .search-results .qn-box .fill-btn-area .fill-btn {
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

#draft .search-results .qn-box .fill-btn-area .active {
  border: solid 2px #3582e7;
  background-color: #3582e7;
  color: #fff;
}

</style>
