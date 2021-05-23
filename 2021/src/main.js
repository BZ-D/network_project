import App from './App.vue'
import router from './router'
import Vue from 'vue'


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount("#app")

router.beforeEach((to, from, next) => {
  if(to.name) {
    document.title = to.name
  }
  next()
})
