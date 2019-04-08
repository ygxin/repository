package com.nsc.service;

import java.util.List;

import com.nsc.pojo.PageUtil;
import com.nsc.pojo.User;

public interface UserService {
	
	List<User> findUserInfo(); //查询列表
	
	User findUserInfobyId(Integer id);//查询id
	
	void saveUser(User user); //保存
	
	void updateUser(User user); //修改用户信息
	
	List<User> findUserName(String userName);//模糊查询姓名
	
	void deleteUser(User user); //删除用户信息
	
	User findName(String userName); //用户名验证
	
	PageUtil findUserPage(Integer start ,Integer end , Integer pagesize);//分页查询
	
	
	
	
	
	
	
}
