package com.nsc.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisConfigtest  {
	@Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
	@Primary
	@Bean
	public RedisCacheManager redisCacheManager(RedisTemplate<String,Object> redisTemplate){
		return new RedisCacheManager(redisTemplate);
	}
	
		/**
	 	@Bean
	    public RedisConnectionFactory redisCF(){
	        //如果什么参数都不设置，默认连接本地6379端口
	        JedisConnectionFactory factory = new JedisConnectionFactory();
	        factory.setPort(6379);
	        factory.setHostName("localhost");
	        return factory;
	    }
	 	/**
	 	@Bean
	    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
	        //创建一个模板类
	        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        //将刚才的redis连接工厂设置到模板类中
	        template.setConnectionFactory(factory);
	        return template;
	    }
		*/
	@Bean	
	public RedisTemplate< String, Object> redisTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper oMapper = new ObjectMapper();
		oMapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		oMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(oMapper);
		template.setValueSerializer(serializer);
		return template;
	}
	
	
	
}
