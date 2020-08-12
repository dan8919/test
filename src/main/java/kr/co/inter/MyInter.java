package kr.co.inter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInter extends HandlerInterceptorAdapter {

	@Override //컨트롤러 실행전에 실행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandle::::::::::::::::::::::::");
		
		HandlerMethod hmethod = (HandlerMethod) handler;
		Method method = hmethod.getMethod();
		System.out.println(hmethod.getBean()); //컨트롤러 정보
		System.out.println(method); //MVC 핸들러로 사용되는 메소드 정보
		
//		return super.preHandle(request, response, handler);
		return true;
	}

	@Override //컨트롤러 실행후에 실행
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle::::::::::::::::::::::::");
		
		//맵처럼 활용
		Object test = modelAndView.getModel().get("test");
//		└컨트롤러에서 넣은 키 입력해서 불러오기
		
		modelAndView.getModel().put("show", "show"); //새로입력
		
		System.out.println(test);
		
//		super.postHandle(request, response, handler, modelAndView);
	}
	
	

}
