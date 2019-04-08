import axios from 'axios' 
let base = '';
export const requestLogin = params => { return axios.post('/api/login/login', params).then(res => res.data);};
export const getUserListPage = params =>{ return axios.get(`/api//user/findUserInfo`,{params,params})}