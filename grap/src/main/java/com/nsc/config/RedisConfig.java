package com.nsc.config;
import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
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

/**
 * redis 配置类
 * @author xyg
 * @param <T>
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
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
