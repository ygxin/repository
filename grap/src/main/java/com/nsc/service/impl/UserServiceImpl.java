package com.nsc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nsc.mapper.UserMapper;
import com.nsc.pojo.PageUtil;
import com.nsc.pojo.Result;
import com.nsc.pojo.User;
import com.nsc.service.UserService;
import com.nsc.util.LoginUtil;
import com.nsc.util.RedisUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;

	@Resource
	private RedisUtil redisUtil;

	@SuppressWarnings("unchecked")
	@Override
	public PageUtil findUserPage(Integer start, Integer end, Integer pagesize) {
		if (redisUtil.hasKey("findUserPage")) {
			System.out.println("分页查询用户信息缓存");
			Integer count = (int) redisUtil.lGetListSize("findUserPage");
			List<User> list = (List<User>) redisUtil.lGet("findUserPage", start, end).get(0);
			return new PageUtil(count, list);
		} else {
			List<User> list = mapper.findUserInfo();
			redisUtil.lSet("findUserPage", list);
			List<User> pagelist = mapper.findUserpage(start, pagesize);
			Integer count = mapper.findCount();
			return new PageUtil(count, pagelist);
		}

	}

	@Override
	public User findUserInfobyId(Integer id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Result saveUser(User user) {
		user.setId(LoginUtil.getUuid());
		user.setCreateDate(LoginUtil.getCurrentTime());
		user.setSalt(LoginUtil.getUuid());
		boolean staue = mapper.saveUser(user);
		Result result = new Result();
		if(staue){
			result.setCode(200);
			result.setMsg("添加成功");
			redisUtil.del("findUserPage");
			List<User> list = mapper.findUserInfo();
			redisUtil.lSet("findUserPage", list);
		}else{
			result.setCode(404);
			result.setMsg("添加失败");
		}
		return result;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findName(String userName) {
		// TODO Auto-generated method stub
		if (redisUtil.hasKey("userfindname")) {
			System.out.println("读取用户登陆缓存");
			return (User) redisUtil.get("userfindname");
		} else {
			User user = mapper.findName(userName);
			redisUtil.set("userfindname", user);
			return user;
		}

	}

	@Override
	public List<User> findUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
