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
        <!--新增界面-->
		<el-dialog title="新增" v-model="dialogVisible " :visible.sync= "dialogVisible">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="姓名" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-radio-group v-model="addForm.sex">
						<el-radio class="radio" :label="1">男</el-radio>
						<el-radio class="radio" :label="0">女</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="年龄">
					<el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
				</el-form-item>
				<el-form-item label="生日">
					<el-date-picker type="date" placeholder="选择日期" v-model="addForm.brithday"></el-date-picker>
				</el-form-item>
				<el-form-item label="地址">
					<el-input type="textarea" v-model="addForm.address"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="dialogVisible  = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
    </section>        
</template>
<script>
import util from '../../common/js/util'
import { getUserListPage } from '../../api/api';
import { addUser } from '../../api/api';
import Qs from 'qs'
import { constants } from 'fs';
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
            limit:10,

            dialogVisible : false,//新增界面是否显示
				addLoading: false,
				addFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
				//新增界面数据
				addForm: {
					name: '',
					sex: -1,
					age: 0,
					birth: '',
					addr: ''
				}
           
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
        handleAdd:function(){
            this.dialogVisible  = true;
            this.addForm={
                name:'',
                sex:-1,
                age:0,
                address:''
            }
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

        },
        addSubmit(){
            this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.addLoading = true;
                            let address = this.addForm.address;
                            let name = this.addForm.name;
                            let age = this.addForm.age;
                            let sex = this.addForm.sex;
                            let brithday = util.formatDate.format(new Date(this.addForm.brithday), 'yyyy-MM-dd');
                            let params = {name:name,address:address,age:age,brithday:brithday,sex:sex};
							addUser(params).then((res) => {
								this.addLoading = false;
								//NProgress.done();
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getUsers();
							});
						});
					}
				});
        }
    },
    mounted() {
			this.getUsers();
		}   
}
</script>
<style>

</style>
