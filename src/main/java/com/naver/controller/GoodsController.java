package com.naver.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;
import kr.co.domain.PurBoardDTO;
import kr.co.service.BoardService;
import kr.co.service.GoodsService;

@Controller
@RequestMapping("goods")
public class GoodsController {
	
	@Inject
	private GoodsService gService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, String curPage) {

		int page = -1;
		if (curPage != null) {
//			└페이지방문시 null인 경우가 많으므로 (속도개선)
			page = Integer.parseInt(curPage);
		} else {
			page = 1;
		}
		
		PageTo<GoodsDTO> to = new PageTo<GoodsDTO>(page);
		
		to = gService.list(to);		
		model.addAttribute("to", to);
		model.addAttribute("list", to.getList());

	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(GoodsDTO gooDto) {
		gService.update(gooDto);
		return "redirect:/goods/list";
	}
	
	@RequestMapping(value = "/update/{gno}", method = RequestMethod.GET)
	public String updateui(@PathVariable("gno") int gno, Model model) {
		GoodsDTO gooDto = gService.updateui(gno);
		model.addAttribute("gooDto", gooDto);
		
		List<CategoryDTO> selectCateList = gService.selectCateList();
		model.addAttribute("selectCateList", selectCateList);		
		
		return "goods/update";
	}	


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(GoodsDTO gooDto) {	
		gService.insert(gooDto);		
		return "redirect:/goods/list";
//		return "redirect:/goods/read/"+cloDto.getGno();
//		return null;
	}
	
//	우선 입력화면 생성하기 위해서 GET 먼저 작성
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert(Model model) {
		List<CategoryDTO> selectCateList = gService.selectCateList();
		model.addAttribute("selectCateList", selectCateList);
		
	}
}
