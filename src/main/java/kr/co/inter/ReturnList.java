package kr.co.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ReturnList extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession sessoin = request.getSession(false);
		/*
		if (sessoin != null) {
			Object obj = sessoin.getAttribute("read");
			if (obj != null) 
				return true;
				
		}
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		*/
		
		
		
		//목록에서 read 링크시
		//1. ~~?curPage=1
		//2. ~~/catePage/1
		//3. ~~/catePage/1?curPage=1
		
		//read 링크 인터셉트
		
		//원래 있던 페이지로 돌아갈 수 있도록 path 받기
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		if (queryString == null) {
			queryString = "";
		} else {
			queryString = "?" + queryString;
		}

		String path = uri + queryString; //원래있던 주소		
		String listPath = "/board/list";
		
		if (path != listPath) {
			sessoin.setAttribute("path", path);
		}
		
		return true;
	}
	
}
