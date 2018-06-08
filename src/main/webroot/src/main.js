// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// import Vue from 'vue'
// import App from './App'
// import router from './router'
// 
// Vue.config.productionTip = false
// 
// /* eslint-disable no-new */
// new Vue({
//   el: '#app',
//   router,
//   components: { App },
//   template: '<App/>'
// })

import Vue from 'vue'
import App from './App'
import router from './router'
import axios from './api/axios.config.js'
import utils from './common/util.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
Vue.prototype.$http = axios
Vue.prototype.$Utils = utils

Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
