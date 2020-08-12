package kr.co.service;

import java.util.List;

import kr.co.domain.AttachDTO;
import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;
import kr.co.domain.PurBoardDTO;

public interface BoardService {
	
	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(int bno);

	BoardVO updateui(int bno);

	void update(BoardVO vo);

	void delete(int bno);

//	PageTo<BoardVO> list(PageTo<BoardVO> to, int cno);
	PageTo<BoardVO> list(PageTo<BoardVO> to);

	List<BoardVO> searchlist(String searchType, String keyword);

	BoardVO searchread(int bno, String searchType, String keyword);

	List<String> getAttach(Integer bno);

	void delAttachFileName(String fileName);

	List<GoodsDTO> selectlist();

	GoodsDTO getGoodsData(int gno);

	PurBoardDTO insertpdto(PurBoardDTO pdto);

	List<PurBoardDTO> plist();

	CategoryDTO getCateData(int cno);

	CategoryDTO selectNjoinCate(int ccno);

	String getCateName(int cno);

	List<CategoryDTO> getCateList(int cno);

	PageTo<BoardVO> catePage(PageTo<BoardVO> to, int cno);

	AttachDTO getAttData(int bno);


}
