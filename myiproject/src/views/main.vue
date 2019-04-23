<template>
  <div class="home">
    <el-row >
      <el-col :span="4">
        <div class="grid-content bg-purple">欢迎登陆</div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple-light"><el-button type="primary" @click="handleclick">点击进入</el-button></div>
      </el-col>
    </el-row>
    <el-carousel indicator-position="outside" height="500px">
      <el-carousel-item v-for="item in items " :key="item.key" class="carouitem">
        <img :src="item.src">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default {
  data() {
    return {
      items: [
        { src: "../../static/img/325202.jpg" },
        { src: "../../static/img/325252.jpg" },
        { src: "../../static/img/325259.jpg" },
        { src: "../../static/img/325260.jpg" },
        { src: "../../static/img/325261.jpg" },
        { src: "../../static/img/325270.jpg" },
        { src: "../../static/img/325272.jpg" },
        { src: "../../static/img/325274.jpg" }
      ]
    };
  },
  methods:{
      handleclick:function(){
          this.$router.push({ path: '/table' });
      }
  }
};
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 50px;
  margin: 0;
}
.home {
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 100%;
}
.bg-purple {
  margin-left: 0px;
  font-size: 30px;
}
.row {
  background-color: #b3c0d1;
}
.bg-purple-light{
    margin-left: 800px;
}
</style>
