package kr.co.persistence;

import java.util.List;

import kr.co.domain.ClothesDTO;

public interface ClothesDAO {

	List<ClothesDTO> selectlist();

	ClothesDTO getGoodsData(int gno);
	

}
