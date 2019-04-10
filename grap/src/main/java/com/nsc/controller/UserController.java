package com.nsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsc.pojo.PageUtil;
import com.nsc.pojo.Result;
import com.nsc.pojo.User;
import com.nsc.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@ResponseBody
	@RequestMapping(value = "/findUserInfo")
	public PageUtil findUsers(Integer page, Integer pagesize) {
		Integer start = 1;
		if (page < 1||page==1) {
			start = 0;

		} else {
			start = 1 + ((page - 1) * pagesize);
		}
		Integer end = (start - 1) + pagesize;
		return userServiceImpl.findUserPage(start, end,pagesize);
	}
	@RequestMapping(value="/saveUser")
	public Result saveUser(User user){
		return userServiceImpl.saveUser(user);
	}
}
