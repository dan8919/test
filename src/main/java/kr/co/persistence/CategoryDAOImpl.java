package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;
import kr.co.domain.CategoryDTO;
import kr.co.domain.PageTo;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Inject
	private SqlSession session;

	private final String NS = "c.a.t";

	@Override
	public CategoryDTO getCateData(int cno) {
		return session.selectOne(NS + ".getCateData", cno);
	}

	@Override
	public List<CategoryDTO> selectCateList() {
		return session.selectList(NS + ".selectCateList");
	}

	@Override
	public CategoryDTO selectNjoinCate(int cno) {
		return session.selectOne(NS + ".selectNjoinCate", cno);
	}

	@Override
	public CategoryDTO getCateName(int cno) {
		return session.selectOne(NS + ".getCateName", cno);
	}

	@Override
	public List<CategoryDTO> getCateList(int cno) {
		// TODO Auto-generated method stub
		return session.selectList(NS + ".getCateList", cno);
	}

	@Override
	public PageTo<BoardVO> catePage(PageTo<BoardVO> to, int cno) {

		RowBounds rowBounds = new RowBounds(to.getStartNum() - 1, to.getPerPage());

		List<BoardVO> list = session.selectList(NS + ".catePage", cno, rowBounds);

		to.setList(list);

		Integer amount = session.selectOne(NS + ".getAmount");
		if (amount != null) {
			to.setAmount(amount);
		} else {
			to.setAmount(0);
		}

		return to;

	}

}
