package com.instaclone.project.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.instaclone.project.response.dto.ResponseDto;
import com.instaclone.project.user.dao.UserDao;
import com.instaclone.project.user.dto.UserLoginDto;
import com.instaclone.project.user.service.UserService;

@Aspect
@Component
public class LoginAspect {
	
	@Autowired
	private UserService service;

	
	// login check 기능
	// 컨트롤러중에 타입이 ResponseDto / 앞에 auth 가 붙은 메소드를 찾아서 검사
	@Around("execution(com.instaclone.project.response.dto.ResponseDto auth*(..))")
	public Object loginCheckJSON(ProceedingJoinPoint joinPoint) throws Throwable {
		// aop 가 적용된 메소드의 값을 Object[] 로 얻어오기
		Object[] args = joinPoint.getArgs();
		// 로그인 여부
		boolean isLogin = false;
		ResponseDto responseDto = null;
		HttpServletRequest request = null;
		
		for(Object tmp:args) {
			//인자로 전달된 값중에 HttpServletRequest type 을 찾아서
			if(tmp instanceof HttpServletRequest) {
				//원래 type 으로 casting
				request=(HttpServletRequest)tmp;
				// client 로 부터 받은 id, token 값을 불러온다. 
				String id=request.getParameter("id");
				String token=request.getParameter("token");
				// db에서 id 와 token 값이 유효한지 확인한다.
				UserLoginDto loginDto = new UserLoginDto();
				loginDto.setId(id);
				loginDto.setToken(token);
				isLogin=service.isLogined(loginDto);
			}
		}
		//로그인 했는지 여부 
		if(isLogin) {
			System.out.println("유효한 사용자 입니다.");
			//aop 가 적용된 메소드 정상 수행하고 리턴된값 리턴해 주기 
			responseDto=(ResponseDto)joinPoint.proceed();
			responseDto.setLogined(true);
			return responseDto;
		}
		System.out.println("유효하지 않은 사용자 입니다.");
		responseDto=new ResponseDto();
		responseDto.setLogined(false);
		responseDto.setMessage("로그인이 필요한 서비스 입니다.");
		return responseDto;
	}
}
