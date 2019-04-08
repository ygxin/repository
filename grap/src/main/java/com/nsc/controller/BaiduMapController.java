package com.nsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/baidumap")
public class BaiduMapController {
	@Autowired
	
	private  RedisTemplate<String, Object> redistemp;
	
	@Cacheable("baidumap")
	@RequestMapping("/baidumap")
	public String test1(){
		System.out.println(redistemp.opsForValue().get("com.nsc.controller.BaiduMapControllertest1"));
		return "index";
	}
}
