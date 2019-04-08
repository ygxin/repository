package com.nsc.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsc.App;
import com.nsc.pojo.User;
import com.nsc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test1(){
		List<User> list = userService.findUserInfo();
		for (User user : list) {
			System.out.println(user);
		}
	}
}
