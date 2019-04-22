// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './routes'
//import Mock from './mock'
import 'font-awesome/css/font-awesome.min.css'
import store from './vuex/store'
//Mock.bootstrap();
// Vue.use(VueRouter)
Vue.use(ElementUi)

Vue.config.productionTip = false

new Vue({
	router,
	store,
  render: h => h(App)
}).$mount("#app")
