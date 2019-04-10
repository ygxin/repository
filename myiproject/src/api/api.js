import axios from 'axios' 
import Qs from 'qs'
let base = '';
export const requestLogin = params => { return axios.post('/api/login/login', params).then(res => res.data);};
export const getUserListPage = params =>{ return axios.get(`/api//user/findUserInfo`,{params,params})}
export const addUser = params =>{
     return axios.post(
         `/api/user/saveUser`,
         Qs.stringify({"name":params.name,"age":params.age,"address":params.address,"brithday":params.brithday,"sex":params.sex})
         )};