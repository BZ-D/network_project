<template>
  <div id="new-question-box">
    <!-- 用于作为创建问卷页面新增的题目组件，这里面放上不同的题型组件 -->
    <single v-if="type==='single'" :index="index"></single>
    <multiple v-else-if="type==='multiple'" :index="index"></multiple>
    <gap_filling v-else-if="type==='gap-fill'" :index="index"></gap_filling>

    <!-- TODO: 在问题区添加删除题目按钮，更改vuex的state中的question数据，可用splice函数 -->
  </div>
</template>

<script>
import single from '@/components/CreateQN/Question-Type/Single'
import multiple from '@/components/CreateQN/Question-Type/Multiple'
import gap_filling from '@/components/CreateQN/Question-Type/GapFilling'


export default {
  name: "NewQuestion",
  props: {
    index: Number, // 当前问题的下标
  },
  computed: {
    //  又踩了一个坑，type必须是计算属性，这样才能做到删除题目时题目信息不会混乱，因为计算属性值是响应式的，data不行
    type() {
      return this.$store.state.questions[this.index].type
    }
  },
  components: {
    single,
    multiple,
    gap_filling
  }
}


</script>

<style scoped>

</style>
