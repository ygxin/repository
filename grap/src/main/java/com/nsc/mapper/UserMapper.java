package com.nsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nsc.pojo.User;

@Mapper
public interface  UserMapper {
    
	List<User> findUserInfo(); //查询列表
	
	User findUserInfobyId(Integer id);//查询id
	
	void saveUser(User user); //保存
	
	void updateUser(User user); //修改用户信息
	
	List<User> findUserName(String userName);//模糊查询姓名
	
	void deleteUser(User user); //删除用户信息

	User findName (String userName);//验证登陆
	
	List<User> findUserpage (@Param("start")int start, @Param("pagesize")int pagesize); //分页查询
	
	Integer findCount(); //查询记录数
	
	
	
}
