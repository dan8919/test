package kr.co.service;

import java.util.List;

import kr.co.domain.CategoryDTO;
import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;

public interface GoodsService {

	void insert(GoodsDTO gooDto);

	List<CategoryDTO> selectCateList();

	PageTo<GoodsDTO> list(PageTo<GoodsDTO> to);
	
	List<GoodsDTO> list();

	GoodsDTO updateui(int gno);

	void update(GoodsDTO gooDto);

}
