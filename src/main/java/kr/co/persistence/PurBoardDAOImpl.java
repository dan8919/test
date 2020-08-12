package kr.co.persistence;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.PurBoardDTO;

@Repository
public class PurBoardDAOImpl implements PurBoardDAO {

	@Inject
	private SqlSession session;
	
	private final String NS = "p.u.r";
	
	@Override
	public PurBoardDTO insertpdto(PurBoardDTO pdto) {
		//pno 값 불러오기
		Integer pno = session.selectOne(NS+".getPno");
		if (pno != null) {
//				└null이 아닌경우가 많이 때문에 속도개선을 위해서
			pno += 1;
		} else {
			pno = 1;
		}		
		pdto.setPno(pno);
		
		//송장번호 만들기
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String code = sdf.format(d);
		
		pdto.setDeliveryCode(98745+code);
		
		session.insert(NS+".insertpdto", pdto);

		return pdto;
		
	}

	@Override
	public List<PurBoardDTO> plist() {
		return session.selectList(NS+".plist");
	}

	

}
