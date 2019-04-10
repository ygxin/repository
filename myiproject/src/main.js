// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import routes from './routes'
//import Mock from './mock'
import 'font-awesome/css/font-awesome.min.css'
import store from './vuex/store'
//Mock.bootstrap();
Vue.use(VueRouter)
Vue.use(ElementUi)

Vue.config.productionTip = false

//设置路由

const router =new VueRouter({
	mode: 'history',
		routes
})
router.beforeEach((to, from, next) => {
	//NProgress.start();
	if (to.path == '/login') {
	  sessionStorage.removeItem('user');
	}
	let user = JSON.parse(sessionStorage.getItem('user'));
	if (!user && to.path != '/login') {
	  next({ path: '/login' })
	} else {
	  next()
	}
  })
/* eslint-disable no-new */
new Vue({
	router,
	store,
  render: h => h(App)
}).$mount("#app")
