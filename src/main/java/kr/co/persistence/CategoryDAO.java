package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.domain.PageTo;

public interface CategoryDAO {

	CategoryDTO getCateData(int cno);

	List<CategoryDTO> selectCateList();

	CategoryDTO selectNjoinCate(int ccno);

	CategoryDTO getCateName(int cno);

	List<CategoryDTO> getCateList(int cno);

	PageTo<BoardVO> catePage(PageTo<BoardVO> to, int cno);

}
