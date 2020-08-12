package com.naver.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.AttachDTO;
import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;
import kr.co.domain.PurBoardDTO;
import kr.co.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Inject
	private BoardService bService;
		
	
	@RequestMapping(value = "/searchread/{bno}", method = RequestMethod.GET)
	public String searchread(@PathVariable("bno") int bno, Model model, String searchType, String keyword) {
		BoardVO vo = bService.searchread(bno, searchType, keyword);
		model.addAttribute("vo", vo);
		model.addAttribute("bno", bno);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		return "board/searchread";
	}
	
	@RequestMapping(value = "/searchlist")
	public String searchlist(Model model, String searchType, String keyword) {
		List<BoardVO> list = bService.searchlist(searchType, keyword);
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);

		return "board/searchlist";
	}

	@RequestMapping(value = "/delete/{bno}")
	public String delete(@PathVariable("bno") int bno) {
		bService.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO vo) {
		bService.update(vo);
		return "redirect:/board/read/"+vo.getBno();
	}
	
	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String updateui(@PathVariable("bno") int bno, Model model) {
		BoardVO vo = bService.updateui(bno);
		model.addAttribute("vo", vo);
		
		List<GoodsDTO> selectlist = bService.selectlist();
		model.addAttribute("selectlist", selectlist);		
		
		return "board/update";
	}	
/*
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public void complete(PurBoardDTO pdto, Model model) {
		//order에서 확인한 내용대로 purchase_board에 입력 수행 
		PurBoardDTO npdto = bService.insertpdto(pdto);

	}*/
	
	@RequestMapping(value = "/order/{bno}", method = RequestMethod.GET)
	public String order(@PathVariable("bno") int bno, Model model) {
		//구매버튼 클릭시 이동
		//purchase_board에 입력할 내용 확인
		PurBoardDTO pdto = new PurBoardDTO();
		BoardVO vo = bService.read(bno);
		int gno = vo.getGno();
		GoodsDTO getGoodsData = new GoodsDTO();
		int cno = vo.getCno();
		getGoodsData = bService.getGoodsData(gno);		
		CategoryDTO getCateData = new CategoryDTO();
		getCateData = bService.getCateData(cno);
		model.addAttribute("vo", vo);
		model.addAttribute("getGoodsData", getGoodsData);
		model.addAttribute("getCateData", getCateData);
		model.addAttribute("pdto", pdto);
		return "board/order";
	}
	/* 기존 order
	@RequestMapping(value = "/order/{bno}", method = RequestMethod.GET)
	public String order(@PathVariable("bno") int bno, Model model) {
		//구매버튼 클릭시 이동
		//purchase_board에 입력할 내용 확인
		PurBoardDTO pdto = new PurBoardDTO();
		BoardVO vo = bService.read(bno);
		int gno = vo.getGno();
		GoodsDTO getGoodsData = new GoodsDTO();
		int cno = vo.getCno();
		getGoodsData = bService.getGoodsData(gno);		
		CategoryDTO getCateData = new CategoryDTO();
		getCateData = bService.getCateData(cno);
		model.addAttribute("vo", vo);
		model.addAttribute("getGoodsData", getGoodsData);
		model.addAttribute("getCateData", getCateData);
		model.addAttribute("pdto", pdto);
		return "board/order";
	}*/
	
	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") int bno, Model model) {
		BoardVO vo = bService.read(bno);
		
		//상품명 불러오기
		int gno = vo.getGno();
		GoodsDTO getGoodsData = new GoodsDTO();
		getGoodsData = bService.getGoodsData(gno);		
		
		//카테명 불러오기
		int cno = vo.getCno();
		CategoryDTO getCateData = new CategoryDTO();
		getCateData = bService.getCateData(cno);
		
		//첨부이미지 불러오기
		AttachDTO getAttData = new AttachDTO();
		getAttData = bService.getAttData(bno);
		
		model.addAttribute("vo", vo);
		model.addAttribute("getGoodsData", getGoodsData);
		model.addAttribute("getCateData", getCateData);
		model.addAttribute("getAttData", getAttData);
		return "board/read";
	}
	
	@RequestMapping(value = "/catePage/{cno}", method = RequestMethod.GET)
	public String catePage(@PathVariable("cno") int cno, Model model, String curPage) {

		int page = -1;
		if (curPage != null) {
			page = Integer.parseInt(curPage);
		} else {
			page = 1;
		}

		PageTo<BoardVO> to = new PageTo<BoardVO>(page);
		to = bService.catePage(to, cno);		
		model.addAttribute("to", to);
		model.addAttribute("list", to.getList());
		
		//각카테고리별 cname, cno 받기 (path, 페이지 제목)
		CategoryDTO getCateData = new CategoryDTO();
		getCateData = bService.getCateData(cno);
		model.addAttribute("getCateData", getCateData);
		

		return "board/catePage";
	}

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, String curPage) {
		
		int page = -1;
		if (curPage != null) {
//			└페이지방문시 null인 경우가 많으므로 (속도개선)
			page = Integer.parseInt(curPage);
		} else {
			page = 1;
		}
		PageTo<BoardVO> to = new PageTo<BoardVO>(page);
//		List<BoardVO> list = bService.list();		
		to = bService.list(to);		
		model.addAttribute("to", to);
		model.addAttribute("list", to.getList());
		
	}


	@Transactional
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {	
//		GoodsDTO gooDto = new GoodsDTO();
//		gService.insert(gooDto);
		bService.insert(vo);		
		return "redirect:/board/read/"+vo.getBno();
//		return null;
	}
	
//	우선 입력화면 생성하기 위해서 GET 먼저 작성
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert(Model model) {
		
		List<GoodsDTO> selectlist = bService.selectlist();
		model.addAttribute("selectlist", selectlist);

		
	}
}
