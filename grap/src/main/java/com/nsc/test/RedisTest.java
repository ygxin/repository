package com.nsc.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsc.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class RedisTest {
	@Autowired
	RedisConnectionFactory factory;
	@Autowired
	RedisTemplate<String, Object> template;
	
	public void testRedis(){
		RedisConnection connection = factory.getConnection();
		connection.set("hello".getBytes(),"world".getBytes());
		System.out.println(new String(connection.get("hello".getBytes())));
	}
	@Test
	public void testRedisTemp(){
		template.opsForValue().set("1", "helloworld");
		System.out.println(template.opsForValue().get("1"));
	}
	  
	    public void testRedisTemplateList(){
	    
	        Map<String, Object> prud  = new  HashMap<String, Object>();
	        prud.put("1", "洗面奶");
	        Map<String, Object> prud1  = new  HashMap<String, Object>();
	        //依次从尾部添加元素
	        template.opsForList().rightPush("pruduct", prud);
	        template.opsForList().rightPush("pruduct", prud1);
	        //查询索引0到商品总数-1索引（也就是查出所有的商品）
	        List<Object> prodList = template.opsForList().range("pruduct", 0,template.opsForList().size("pruduct")-1);
	        for(Object obj:prodList){
	            System.out.println(obj);
	        }
	        System.out.println("产品数量:"+template.opsForList().size("pruduct"));
	        
	    }
	
	
	
}
