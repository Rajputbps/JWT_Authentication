package com.jwtAuth.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CustomeConfig extends OncePerRequestFilter{

	public CustomeConfig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"authorization, content-type, xsrf-token, Cache-Control, remember-me, WWW-Authenticate");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		filterChain.doFilter(request, response);
		
	}

}
