<template>
  <div id="profile">
    <div class="mask" v-show="isLoggingOut">
      <!-- 作用是突出显示确认登出窗口，让其他元素变为灰色不可选 -->
    </div>

    <div class="info-box">
      <span class="greeting-banner">
        <h2>欢迎您，{{ userName }}！</h2>
      </span>

      <h2 class="up-to-now">截至目前：</h2>

      <span class="info released-info">
        <span>您已发布了{{ numOfReleased }}份问卷:
          <span :class="{mouse_on_check: mouseOnReleased}" class="go-to-manage-btn" @click="checkReleased"
                @mouseover="mouseOnReleased=true" @mouseleave="mouseOnReleased=false">点此查看</span>
        </span>

      </span>

      <span class="info filled-info">
        <span>您已填写了{{ numOfFilled }}份问卷:
          <span :class="{mouse_on_check: mouseOnFilled}" class="go-to-manage-btn" @click="checkFilled"
                @mouseover="mouseOnFilled=true" @mouseleave="mouseOnFilled=false">点此查看</span>
        </span>
      </span>

      <span class="info draft-info">
        <span>您还有{{ numOfDrafts }}份问卷未发布:
          <span :class="{mouse_on_check: mouseOnDraft}" class="go-to-manage-btn" @click="checkDrafts"
                @mouseover="mouseOnDraft=true" @mouseleave="mouseOnDraft=false">点此继续编辑</span>
        </span>
      </span>

      <div :class="{logout_active: mouseOnLogout}" class="log-out-btn" @mouseover="mouseOnLogout=true"
           @mouseleave="mouseOnLogout=false" @click="isLoggingOut=true">
        登&nbsp;出
      </div>
    </div>

    <div class="logout-confirm-box" v-show="isLoggingOut">
      <div class="hint">您确定要登出吗？</div>
      <div class="btn-area">
        <span :class="{cancel_active: mouseOnCancel}" class="cancel" @mouseover="mouseOnCancel=true"
              @mouseleave="mouseOnCancel=false" @click="isLoggingOut=false">取 消</span>
        <span :class="{confirm_active: mouseOnConfirm}" class="confirm" @mouseover="mouseOnConfirm=true"
              @mouseleave="mouseOnConfirm=false" @click="logout">确 定</span>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      isLoggingOut: false,
      userName: '学长',
      numOfReleased: 5,  // 已发布的问卷份数
      numOfFilled: 6,   //  已填写的问卷份数
      numOfDrafts: 3,   //  保存的草稿数

      mouseOnReleased: false,
      mouseOnFilled: false,
      mouseOnDraft: false,

      mouseOnLogout: false,
      mouseOnCancel: false,
      mouseOnConfirm: false,
    }
  },
  methods: {
    logout() {
      // 用户登出，删除当前用户网页信息，推入主页面
      // TODO: 前端删除用户信息

      // 退回主界面
      this.$router.push('/')
    },
    checkReleased() {
      // 跳转到管理页面的已发布分支
      this.$router.push('/main/manage/released')
    },
    checkFilled() {
      // 跳转到管理页面的已填写分支
      this.$router.push('/main/manage/filled')
    },
    checkDrafts() {
      // 跳转到管理页面的草稿箱分支
      this.$router.push('/main/manage/draft')
    }
  }
}
</script>

<style scoped>

#profile .mask {
  z-index: 997;

  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;

  position: fixed;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.49);
}

#profile .logout-confirm-box {
  z-index: 999;

  position: fixed;
  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;

  width: 300px;
  height: 120px;
  border-radius: 25px;
  background-color: #fff;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#profile .logout-confirm-box .hint {
  font-size: 20px;
}

#profile .logout-confirm-box .btn-area {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#profile .logout-confirm-box .btn-area .cancel {
  cursor: pointer;

  color: gray;

  border: 2px solid gray;
  width: 60px;
  height: 25px;

  display: flex;
  align-items: center;
  justify-content: center;
}

#profile .logout-confirm-box .btn-area .cancel_active {
  color: #fff;

  border: 2px solid gray;
  background: gray;
}

#profile .logout-confirm-box .btn-area .confirm {
  cursor: pointer;

  border: 2px solid red;
  width: 60px;
  height: 25px;

  color: red;

  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 40px;
}

#profile .logout-confirm-box .btn-area .confirm_active {
  color: #fff;

  border: 2px solid #bd0101;
  background: #bd0101;
}


#profile .info-box {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;

  width: 500px;
  height: 600px;
  border-radius: 25px;

  background-color: #fff;
}

#profile .info-box .greeting-banner {
  margin: 0 auto 30px;
}

#profile .info-box .greeting-banner h2 {
  font-size: 40px;
  font-weight: bolder;
  font-family: "STZhongsong", "STHeiti", serif;

  cursor: default;

  background-image: linear-gradient(120deg, #4c1fdb, #d91592);
  -webkit-background-clip: text;
  color: transparent;
}

#profile .info-box .up-to-now {
  cursor: default;

  margin-left: 30px;
  margin-bottom: 30px;
  font-size: 25px;

  background-image: linear-gradient(180deg, #474747, #c4c4c4);
  -webkit-background-clip: text;
  color: transparent;
}

#profile .info-box .info {
  display: flex;
  align-items: center;
  justify-content: center;

  cursor: default;

  width: 400px;
  height: 40px;
  margin: 0 auto 20px;

  box-shadow: 1px 1px 1px 1px #c1c1c1;

  font-size: 20px;
}

#profile .info-box .go-to-manage-btn {
  cursor: pointer;
  color: #1398d7;
}

#profile .info-box .mouse_on_check {
  text-decoration: underline;
}

#profile .info-box .log-out-btn {
  margin: 60px auto 0;
  cursor: pointer;

  color: red;
  width: 150px;
  height: 30px;
  border: 2px solid red;

  display: flex;
  align-items: center;
  justify-content: center;

  font-weight: bold;
}

#profile .info-box .logout_active {
  color: #fff;
  border: 2px solid #bd0101;
  background-color: #bd0101;
}
</style>
