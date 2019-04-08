package com.nsc.filter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
@Component
@WebFilter(filterName="loginFilter",urlPatterns="/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		request.getRemotePort();
		HttpServletRequest request2 = (HttpServletRequest) request;
		String url = request2.getRequestURL().toString();
		System.out.println(url);
		    HttpServletResponse response2 = (HttpServletResponse) response;
	        response2.setHeader("Access-Control-Allow-Origin", "*");
	        response2.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        //response.setHeader("Access-Control-Max-Age", "3600");
	        response2.setHeader("Access-Control-Allow-Headers", ":x-requested-with,content-type");
	        //((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Origin", "*");
	        filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	

}
