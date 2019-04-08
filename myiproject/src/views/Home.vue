<template>
    <el-container class="container">
        <el-header>
            <el-col :span="4" class="logo-collapse-width" style="font-size:28px;font-family:Microsoft YaHei">
              <i class="fa fa-television"></i>
              {{sysName}}
            </el-col>
           
             <span style=" margin-left: 900px"><i class="fa fa-user-circle-o"></i>{{sysUserName}}</span>
             <span ><i  style="margin-left: 50px" class="fa fa-sign-out" @click="logout" aria-hidden="true"></i></span>
        </el-header>
        <el-container>
              <aside :class="collapsed?'menu-collapsed':'menu-expanded'">
                <!--导航菜单-->
                <el-menu :default-active="$route.path" class="el-menu-vertical-demo" @open="handleopen" @close="handleclose" @select="handleselect"
                  unique-opened router v-show="!collapsed">
                  <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden">
                    <el-submenu :index="index+''" v-if="!item.leaf">
                      <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
                      <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.hidden" @click="clickMenu(child.path)">{{child.name}}</el-menu-item>
                    </el-submenu>
                    <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path " @click="clickMenu(item.children[0].path)"><i :class="item.iconCls"></i>{{item.children[0].name}}</el-menu-item>
                  </template>
                </el-menu>
                <!--导航菜单-折叠后-->
                <ul class="el-menu el-menu-vertical-demo collapsed" v-show="collapsed" ref="menuCollapsed">
                  <li v-for="(item,index) in $router.options.routes" v-if="!item.hidden" class="el-submenu item">
                    <template v-if="!item.leaf">
                      <div class="el-submenu__title" style="padding-left: 20px;" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"><i :class="item.iconCls"></i></div>
                      <ul class="el-menu submenu" :class="'submenu-hook-'+index" @mouseover="showMenu(index,true)" @mouseout="showMenu(index,false)"> 
                        <li v-for="child in item.children" v-if="!child.hidden" :key="child.path" class="el-menu-item" style="padding-left: 40px;" :class="$route.path==child.path?'is-active':''" @click="$router.push(child.path)">{{child.name}}</li>
                      </ul>
                    </template>
                    <template v-else>
                      <li class="el-submenu">
                        <div class="el-submenu__title el-menu-item" style="padding-left: 20px;height: 56px;line-height: 56px;padding: 0 20px;" :class="$route.path==item.children[0].path?'is-active':''" @click="$router.push(item.children[0].path)"><i :class="item.iconCls"></i></div>
                      </li>
                    </template>
                  </li>
                </ul>
              </aside>
              <el-main>
                <section class="content-container">
                  <div class="grid-content bg-purple-light">
                    <el-col :span="24" class="breadcrumb-container">
                      <el-breadcrumb separator="/" class="breadcrumb-inner">
                        <el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
                          {{ item.name }}
                        </el-breadcrumb-item>
                      </el-breadcrumb>
                    </el-col>
                    <el-col :span="24" class="content-wrapper">
                      <transition name="fade" mode="out-in">
                        <router-view></router-view>
                      </transition>
                    </el-col>
                  </div>
                </section>
              </el-main>
        </el-container>
    </el-container>
</template>
<style>
 .container{
   position: absolute;
		top: 0px;
		bottom: 0px;
		width: 100%;
 }
 .el-header{
    background-color: #409EFF;
    color: #333;
    text-align: center;
    line-height: 60px
  }
  .el-footer {
    color:#909399;
    text-align: center;
    line-height: 50px;
  }

  .el-aside {
    color: #333;
    text-align: center;
    line-height: 390px;
    background-color:#79BBFF
    
  }

   .el-main {
    color: #333;
    text-align: center;
  }
  .dropdown{
    margin-left: 1100px
  }
</style>

<script>
  export default {
    data() {
      return {
        sysName:'欢迎登陆',
        collapsed:false,
        sysUserName:'',
        sysUserAvatar:''
      }
    },
    methods:{
      logout:function(){
       this.$confirm('确认退出吗?','提示',{
       }).then(()=>{
         sessionStorage.removeItem('user');
         this.$router.push('/login');
       }).catch(()=>{

       })
      },
       handleopen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleclose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleselect: function (a, b) {
			},
      
      clickMenu(i){
        this.$router.push({path:i});
      },
      collapse:function(){
				this.collapsed=!this.collapsed;
			},
			showMenu(i,status){
				this.$refs.menuCollapsed.getElementsByClassName('submenu-hook-'+i)[0].style.display=status?'block':'none';
			}
    },
    mounted(){
        var user = sessionStorage.getItem('user');
			  if (user) {
          user = JSON.parse(user);
          this.sysUserName = user.name || '';
          this.sysUserAvatar = user.avatar || '';
        }

    }
  };
</script>