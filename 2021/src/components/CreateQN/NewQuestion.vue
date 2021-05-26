<template>
  <div id="new-question-box">
    <!-- 用于作为创建问卷页面新增的题目组件，这里面放上不同的题型组件 -->
    <single v-if="type==='single'"></single>
    <multiple v-else-if="type==='multiple'"></multiple>
    <gap_filling v-else-if="type==='gap-fill'"></gap_filling>
  </div>
</template>

<script>
import single from '@/components/CreateQN/Question-Type/Single'
import multiple from '@/components/CreateQN/Question-Type/Multiple'
import gap_filling from '@/components/CreateQN/Question-Type/GapFilling'


export default {
  name: "NewQuestion",
  data() {
    return {
      type: this.$store.state.questionAddingInfo.type
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
