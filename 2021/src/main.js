import App from './App.vue'
import router from './router'
import Vue from 'vue'
import vuetify from 'vuetify'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount("#app")
