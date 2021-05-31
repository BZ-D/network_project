<template>
  <div id="search">
    <div class="search-box">
      <span class="search-area">
        <input :class="{beyond_limit: beyond_limit}" type="text" :placeholder="placeholder" v-model="searchContent">
      </span>
      <span class="search-type">
        <select name="search-type" id="" @change="changeSearchType($event)" class="options">
          <option value="name-of-qn">按问卷名称</option>
          <option value="name-of-user">按发布人名称</option>
        </select>
      </span>
      <span class="search-icon">
        <img v-show="!mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜.png')"
             @mouseover="mouseOnSearch=true" alt="">
        <img v-show="mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜mouseon.png')" @click="searchQN"
             @mouseleave="mouseOnSearch=false" alt="">
      </span>
    </div>
  </div>
</template>

<script>
import {searchByTitle, searchByUser} from "@/api/searchQN";

export default {
  name: "SearchQN",
  computed: {
    beyond_limit() {
      return this.searchContent.length > 25
    }
  },
  data() {
    return {
      mouseOnSearch: false,
      placeholder: '请在此输入问卷名称',
      searchContent: ''
    }
  },
  methods: {
    changeSearchType(event) {
      this.placeholder = event.target.value === 'name-of-qn' ? '请在此输入问卷名称' : '请在此输入发布人名称'
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


      // if (this.placeholder === '请在此输入问卷名称') {
      //   // 按问卷名称搜索
      //   searchByTitle({searchContent: this.searchContent}).then(res => {
      //     if (搜索成功) {
      //       显示所有搜索结果，要求res返回所有相关的问卷信息
      //       包括问卷id、问卷名称、问卷包含的题目数组、发布用户的id、发布用户的昵称，作为一个对象返回
      //     } else {
      //       显示搜索失败相关信息
      //     }
      //   })
      // } else {
      //   searchByUser({searchContent: this.searchContent}).then(res => {
      //     if (搜索成功) {
      //       显示所有搜索结果，要求res返回所有相关的问卷信息
      //       包括问卷id、问卷名称、问卷包含的题目数组、发布用户的id、发布用户的昵称，作为一个对象返回
      //     } else {
      //       显示搜索失败相关信息
      //     }
      //   })
      // }
    }
  }
}
</script>

<style scoped>

#search .search-box {
  width: 700px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;

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

</style>
