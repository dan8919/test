package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.ClothesDTO;
import kr.co.domain.GoodsDTO;
import kr.co.domain.PageTo;

@Repository
public class GoodsDAOImpl implements GoodsDAO {

	@Inject
	private SqlSession session;
	
	private final String NS = "g.o.o";

	@Override
	public void insert(GoodsDTO gooDto) {
		//gno 불러오기 
		Integer gno = session.selectOne(NS+".getGno");		
		if (gno != null) {
			gno += 1;
		} else {
			gno = 1;
		}	

		gooDto.setGno(gno);

		
		session.insert(NS+".insert", gooDto);

	}
	

	@Override
	public PageTo<GoodsDTO> list(PageTo<GoodsDTO> to) {
		RowBounds rowBounds = new RowBounds(to.getStartNum()-1, to.getPerPage());

		List<GoodsDTO> list = session.selectList(NS+".list", null, rowBounds);
		
		to.setList(list);
		
		Integer amount = session.selectOne(NS+".getAmount");
		if (amount != null) {
			to.setAmount(amount);
		} else {
			to.setAmount(0);
		}

		return to;

	}

	@Override
	public List<GoodsDTO> list() {
		return session.selectList(NS+".list");
	}


	@Override
	public GoodsDTO getGoodsData(int gno) {
		return session.selectOne(NS+".getGoodsData", gno);
	}


	@Override
	public List<GoodsDTO> selectlist() {
		return session.selectList(NS+".selectlist");
	}


	@Override
	public GoodsDTO updateui(int gno) {
		return session.selectOne(NS+".updateui", gno);
	}


	@Override
	public void update(GoodsDTO gooDto) {
		session.update(NS+".update", gooDto);
//		session.up
	}
	
	
}
