import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Table from './views/nav1/Table.vue'
import Form from './views/nav1/Form.vue'
import users from './views/nav2/Users.vue'
import role from './views/nav2/Role.vue'
import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'
import VueRouter from 'vue-router'
import Vue from 'vue'
Vue.use(VueRouter);

const router = new VueRouter({
    routes:[
        {
            path: '/login',
            component: Login,
            name: '',
            hidden: true
        },
        {
            path: '/404',
            component: NotFound,
            name: '',
            hidden: true
        },
        {
            path: '/main',
            component: Main,
            name: '主页',
        },
        //{ path: '/main', component: Main },
        {
            path: '/',
            component: Home,
            name: '菜单展示',
            iconCls: 'fa fa-bars',//图标样式class
            children: [
                { path: '/table', component: Table,iconCls:'fa fa-table', name: '列表' },
                { path: '/form', component: Form, iconCls:'fa fa-list-alt', name: '表单' },
            ]
        },
        {
            path: '/',
            component: Home,
            name: '系统设置',
            iconCls: 'fa fa fa-cog',
            children: [
                { path: '/users', component: users, name: '用户管理' },
                { path: '/role', component: role, name: '角色管理' }
            ]
        },
        {
            path: '/',
            component: Home,
            name: '',
            iconCls: 'fa fa-address-card',
            leaf: true,//只有一个节点
            children: [
                { path: '/page6', component: Page6, name: '备忘' }
            ]
        },
        {
            path: '/',
            component: Home,
            name: 'Charts',
            iconCls: 'fa fa-bar-chart',
            children: [
                { path: '/echarts', component: echarts, name: 'echarts' }
            ]
        },
        {
            path: '*',
            hidden: true,
            redirect: { path: '/404' }
        }
    ],
	mode: 'history',
		
})
router.beforeEach((to, from, next) => {
	//NProgress.start();
	if (to.path == '/login') {
	  next();
	}else{
        let token = localStorage.getItem('token'); // 获取token
        let time = localStorage.getItem('time') ?localStorage.getItem('time'):0; //判断token存入时间
        let newtime = (new Date()).getTime();  //当前token
        let Halfhour = 60*60*30;               //30分钟
        if(token=='' || token == null){
            next('/login');
        }else{
            if(newtime-time<Halfhour){
                next();
            }else{
                next('/login');
            }
           
        }
    }
	
  })
export default router;