package kr.co.persistence;

import java.util.List;

import kr.co.domain.PurBoardDTO;

public interface PurBoardDAO {

	PurBoardDTO insertpdto(PurBoardDTO pdto);

	List<PurBoardDTO> plist();
	

}
