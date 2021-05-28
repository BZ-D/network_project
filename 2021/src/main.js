import App from './App.vue'
import router from './router'
import Vue from 'vue'
import store from './store'
import {sync} from "vuex-router-sync";

sync(store, router)

Vue.config.productionTip = false
Vue.prototype.$store = store

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app")

router.beforeEach((to, from, next) => {
  if (to.name) {
    document.title = to.name
  }
  next()
})
