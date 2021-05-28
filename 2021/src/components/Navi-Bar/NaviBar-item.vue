<template>
  <div class="navi-bar-item">
    <div :class="{mouse_on: isMouseOver}" class="navi-bar-item-wrap" @click="changePage"
         @mouseover="isMouseOver=true"
         @mouseleave="isMouseOver=false">
      <span v-show="isActive===1">
        <slot name="normalImg"></slot>
      </span>
      <span v-show="isActive===2">
        <slot name="activeImg"></slot>
      </span>
      <span v-show="isActive===3">
        <slot name="mouseOnImg"></slot>
      </span>
      <span v-text="title" :class="{active: isActive===2}" class="navi-bar-item-text"></span>
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
  data() {
    return {
      isMouseOver: false,
    }
  },
  computed: {
    isActive() {
      // 取值有三个
      // 1表示正常状况，即既没有点击又没有将鼠标放到上边
      // 2表示点击，变红
      // 3表示鼠标放在上边，变灰
      if (this.sel === this.page) {
        return 2
      } else {
        if (this.isMouseOver) {
          return 3
        } else {
          return 1
        }
      }
    },
  },
  methods: {
    changePage() {
      this.$emit('navi-bar-item-click', this.page)
    }
  }
}
</script>

<style>
.navi-bar-item .mouse_on {
  color: #878787;
}

.navi-bar-item .navi-bar-item-wrap {
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
