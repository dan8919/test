package kr.co.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		//로그인여부 받기
		HttpSession session = request.getSession(false);		
		Object login = session.getAttribute("login");
//		└login이라는 이름의 객체가 바인딩 된게 없으면 null */

		HttpSession session = request.getSession();	//세션값 새로 받을수있도록
		Object login = session.getAttribute("login");		
		
		if (login == null) {
			//로그인이 안되어있으면 -->로그인 페이지로
			
			//로그인 후 있었던곳으로 돌아갈수 있도록 path 받기
			String uri = request.getRequestURI();
			String queryString = request.getQueryString();
			
//			/member/update/m001?id=m001&pw=1
//			uri : /member/update/m001
//			queryString : id=m001&pw=1
//			uri+queryString : /member/update/m001id=m001&pw=1
//			└"?"가 없음
			
			if (queryString == null) {
				queryString = "";
			} else {
				queryString = "?" + queryString;
			}
			
			if (request.getMethod().equals("GET")) {
				String path = uri + queryString;
				session.setAttribute("path", path);				
			}
			
			
			response.sendRedirect("/member/login");
			return false;
		}
		
		return true; //컨트롤러 수행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
}
