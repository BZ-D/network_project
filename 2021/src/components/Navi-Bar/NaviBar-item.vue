<template>
  <div class="navi-bar-item">
    <div class="navi-bar-item-wrap" @click="changePage">
      <span v-show="!isActive">
    <slot name="normalImg"></slot>
  </span>
      <span v-show="isActive">
    <slot name="activeImg"></slot>
  </span>
      <span v-text="title" :class="{active: isActive}" class="navi-bar-item-text"></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "NaviBar-item",
  props: {
    sel: String,
    page: String,
    title: String,
  },
  computed: {
    isActive() {
      return this.sel === this.page
    }
  },
  methods: {
    changePage() {
      this.$emit('navi-bar-item-click', this.page)
      this.$router.push('/main/' + this.page)
    }
  }
}
</script>

<style>
.navi-bar-item .navi-bar-item-wrap{
  flex-grow: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  cursor: pointer;
  padding-top: 10px;
}

.navi-bar-item .navi-bar-item-wrap span {
  font-size: 20px;
  font-weight: normal;
  font-family: "STZhongsong", "STHeiti", serif;
}

.navi-bar-item .navi-bar-item-wrap .navi-bar-item-text, .navi-bar-item img {
  cursor: pointer;

}

.active {
  color: #d4237a;
}
</style>
