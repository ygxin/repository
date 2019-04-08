<template>
    <section>
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="姓名" ></el-input>
                </el-form-item>
                <el-form-item>
                   <el-button type="primary" v-on:click="getUsers">查询</el-button>
                </el-form-item>
                <el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
            </el-form>
        </el-col>
        <!--列表-->
		<el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="name" label="姓名" width="120" sortable>
			</el-table-column>
			<el-table-column prop="sex" label="性别" width="100" :formatter="formatSex" sortable>
			</el-table-column>
			<el-table-column prop="age" label="年龄" width="100" sortable>
			</el-table-column>
			<el-table-column prop="brithday" label="生日" width="120" sortable>
			</el-table-column>
			<el-table-column prop="address" label="地址" min-width="180" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template slot-scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
        <el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="10" :total="total" style="float:right;">
			</el-pagination>
		</el-col>
    </section>        
</template>
<script>
import util from '../../common/js/util'
import { getUserListPage } from '../../api/api';
export default {
    name:'Table',
    data(){
        return {
            filters:{
            name:''
            },
            users:[],
            total:0,
            page:1,
            listLoading : false,
            sels: [],//列表选中列,
            limit:10
           
        }
    },
    methods:{
        getUsers(){
            var params = new URLSearchParams();
            params.append('page', this.page);
            params.append('pagesize', this.limit);
				this.listLoading = true;
                //NProgress.start();
				getUserListPage(params).then((res) => {
					this.total = res.data.total;
					this.users = res.data.list;
					this.listLoading = false;
					//NProgress.done();
				}); 
        },
        handleAdd(){

        },
        handleEdit(){

        },
        handleDel(){

        },
        selsChange: function (sels) {
				this.sels = sels;
        },
        formatSex: function (row, column) {
				return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
        },
        handleCurrentChange(val) {
			this.page = val;
			this.getUsers();
        },
        batchRemove:function(){

        }
    },
    mounted() {
			this.getUsers();
		}   
}
</script>
<style>

</style>
