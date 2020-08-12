package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")
//@SessionAttributes({"id", "pw"}) //�꽭�뀡�뿉 諛붿씤�뵫�븷�닔 �엳�뒗 媛앹껜 �뿬�윭
@SessionAttributes({"login"}) //紐⑤뜽�뿉 "login"�씠�씪�뒗 �궎媛� 媛앹껜媛� �엳�쑝硫� 諛붿씤�뵫 �떆�궎湲�
public class MemberController {
	
	@Autowired //�뼱�뵖媛� 媛앹껜媛� �엳�떎 --null�씠 �븘�떂 (service �뼱�끂�뀒�씠�뀡 �벑 ...)
	private MemberService memberService;
//			�뵒�씤�꽣�럹�씠�뒪 援ы쁽
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus status) {		
		status.setComplete();		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/loginpost", method = RequestMethod.POST)
	public String loginpost(LoginDTO login, Model model, HttpSession session) {
		//dto�뿉 濡쒓렇�씤媛� �꽔怨�
		MemberDTO dto = memberService.loginpost(login);
		

		if (dto != null) {			
			//not null = 濡쒓렇�씤 �맖 : 濡쒓렇�씤媛� 遺덈윭�삤湲�
			model.addAttribute("login", dto);
			
			//authinter �씤�꽣�뀎�꽣 �뿉�꽌 諛쏆쓣 path
			String path = (String) session.getAttribute("path");
			
			if (path != null) {
				//authinter �씤�꽣�뀎�꽣�뿉 嫄몃졇�쓣 寃쎌슦�뿉留�
//				(鍮꾨줈洹몄씤 �떆�룄 �븣臾몄뿉 濡쒓렇�씤 �솕硫� �뱾�뼱�솕�쓣 寃쎌슦)
				return "redirect:"+path;
			}
//			(洹몄쇅�뿉�뒗 �씪諛� 濡쒓렇�씤 泥섎━)			
			return "redirect:/board/list"; //�굹以묒뿉 寃뚯떆�뙋 由ъ뒪�듃�굹 硫붿씤�솕硫댁쑝濡� ��泥�			
		} else {
			//null = 濡쒓렇�씤 �떎�뙣 : 濡쒓렇�씤�솕硫댁쑝濡�
			return "redirect:/member/login";			
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		//login jsp
	}

	//delete
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		memberService.delete(id);
		return "redirect:/member/list";
	}
	
	
	//update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberDTO dto) {
		System.out.println(dto.toString());
		memberService.update(dto);
		return "redirect:/member/list";
	}	
	
	
	//updateui
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//									  �뵒�룞�쟻 �뜲�씠�꽣
	public String updateui(@PathVariable("id") String id, Model model) {
		MemberDTO dto = memberService.updateui(id);
		model.addAttribute("dto", dto);
		return "member/update";
	}

	
	//selectById
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public String read(@PathVariable("id") String id, Model model) {
		MemberDTO dto = memberService.read(id);
		model.addAttribute("dto", dto); //setAttribute �뿭�븷
		return "member/read";
	}
	
	
	//select
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO> list = memberService.list();
		model.addAttribute("list", list);
	}
	
	
	
	//insert	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO dto) {
		
		int idChk = memberService.idChk(dto);
		
		if (idChk == 1) {
			return "/member/insert";
		} else if (idChk == 0) {
			memberService.insert(dto);
		}
		
		return "redirect:/member/list";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		return "member/insert";
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.POST)
	public int idChk(MemberDTO dto) {
		return memberService.idChk(dto);
	}
	
}
