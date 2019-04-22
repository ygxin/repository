package com.nsc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsc.pojo.Result;
import com.nsc.pojo.User;
import com.nsc.service.UserService;
import com.nsc.util.LoginUtil;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public Result login(HttpServletRequest request, HttpServletResponse response,String userName ,String password) {
		Result result = new Result();
		if(userName!=null&&password!=null){
			User user = userService.findName(userName);
			if(user!= null){
				String checkpassword = LoginUtil.MD5Encode(password+user.getSalt());
				if(user.getPassword().equals(checkpassword)){
					result.setMsg("登陆成功");
					result.setCode(200);
					result.setObject(user);
					result.setToken(LoginUtil.getUuid());
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setMaxInactiveInterval(60 * 60 * 2); // 单位秒
				}else{
					result.setMsg("密码不正确");
				}
			}else{
				result.setMsg("用户名不正确");
			}
			
		}else{
			 result.setMsg("请填写用户名密码");
		} 
		return result; 
	} 
}
