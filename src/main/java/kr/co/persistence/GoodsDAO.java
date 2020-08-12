package kr.co.persistence;

import java.util.List;

import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;

public interface GoodsDAO {

	void insert(GoodsDTO gooDto);

	List<GoodsDTO> list();

	PageTo<GoodsDTO> list(PageTo<GoodsDTO> to);

	GoodsDTO getGoodsData(int gno);

	List<GoodsDTO> selectlist();

	GoodsDTO updateui(int gno);

	void update(GoodsDTO gooDto);



}
