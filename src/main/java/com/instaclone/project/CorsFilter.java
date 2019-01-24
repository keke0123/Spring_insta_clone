package com.instaclone.project;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("filter");
//		response.addHeader("Access-Control-Allow-Origin", "*");
//        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())); {
//        	System.out.println("if");
//	        // CORS "pre-flight" request
//	        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");	
//	        response.addHeader("Access-Control-Allow-Headers", "Authorization");
//	        response.addHeader("Access-Control-Max-Age", "1728000");
//        }
////        filterChain.doFilter(request, response);
		
		// 되긴 되는데 값이 전송이 안됨;;;
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age","3600");
//        response.setHeader("Access-Control-Allow-Headers","Content-Type, Accept, X-Requested-With, remember-me");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin,Access-Control-Allow-Credentials");
		
//		response.setHeader("Access-Control-Allow-Origin","*");
//		response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age","3600");
//		response.setHeader("Access-Control-Allow-Headers","Content-Type, Accept, X-Requested-With, remember-me");
         
        filterChain.doFilter(request, response);
		
	}
}
