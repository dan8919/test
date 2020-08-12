package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.ClothesDTO;

@Repository
public class ClothesDAOImpl implements ClothesDAO {
	
	@Inject
	private SqlSession session;
	
	private final String NS = "c.l.o";

	@Override
	public List<ClothesDTO> selectlist() {

		return session.selectList(NS+".selectlist");
	}

	@Override
	public ClothesDTO getGoodsData(int gno) {
		return session.selectOne(NS+".getGoodsData", gno);
	}

}
