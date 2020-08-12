package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.utils.Utils;

@Controller
public class MyController {

	
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(String name) {
		name = Utils.toKor(name);
		System.out.println(name);
		
	}

	
	@RequestMapping(value = "good", method = RequestMethod.GET)
	public void good(Model model) {
		model.addAttribute("test", "test 입니다");
		
	}

	
	@RequestMapping(value = "world", method = RequestMethod.GET)
	public void world() {
//			└반환형이 없으면 밸류값이 view 페이지의 이름이 된다

	}
		
		
	
	//MVC핸들러
//	@RequestMapping(value = {"hello", "HELLO"}) //배열로도 입력가능
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
//		└반환형은 view 페이지의 이름
	}
	
}
