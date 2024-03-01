import Vue from 'vue'
import App from './App.vue'
import router from'./router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import request from "@/net/request.js";

Vue.config.productionTip = false

Vue.prototype.$request=request
Vue.prototype.$baseUrl = 'http://localhost:8080'


Vue.use(Element, {size: "small"})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

