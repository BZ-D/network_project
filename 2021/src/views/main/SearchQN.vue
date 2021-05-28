<template>
  <div id="search">
    <div class="search-box">
      <span class="search-area">
        <input type="text" :placeholder="placeholder">
      </span>
      <span class="search-type">
        <select name="search-type" id="" @change="changeSearchType($event)">
          <option value="name-of-qn">按问卷名称</option>
          <option value="name-of-user">按发布人名称</option>
        </select>
      </span>
      <span class="search-icon">
        <img v-show="!mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜mouseon.png')"
            @mouseover="mouseOnSearch=true" alt="">
        <img v-show="mouseOnSearch" :src="require('@/assets/img/navi-bar/放大镜.png')"
             @mouseleave="mouseOnSearch=false" alt="">
      </span>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchQN",
  data() {
    return {
      mouseOnSearch: false,
      placeholder: '请在此输入问卷名称',
    }
  },
  methods: {
    changeSearchType(event) {
      this.placeholder = event.target.value === 'name-of-qn' ? '请在此输入问卷名称' : '请在此输入发布人名称'
    }
  }
}
</script>

<style scoped>


</style>
