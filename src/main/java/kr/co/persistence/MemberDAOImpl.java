package kr.co.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

@Repository //媛앹껜瑜� 留뚮뱾寃좊떎�뒗 �몴�떆
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession session;
	
	private final String NS="m.e.m";
	
	

	@Override
	public void insert(MemberDTO dto) {
//		session.insert(statement, parameter);
//						�뵒mapper�쓽 id
		session.insert(NS+".insert", dto);
		
	}



	@Override
	public List<MemberDTO> list() {
//		return session.selectList(statement, parameter, rowBounds); //�럹�씠吏뺤쿂由� 寃쎌슦
		return session.selectList(NS+".list");
	}



	@Override
	public MemberDTO read(String id) {
		return session.selectOne(NS+".read", id);
	}



	@Override
	public MemberDTO updateui(String id) {
		return session.selectOne(NS+".updateui", id);
	}



	@Override
	public void update(MemberDTO dto) {
		session.update(NS+".update", dto);
		
	}



	@Override
	public void delete(String id) {
		session.delete(NS+".delete", id);
	}



	@Override
	public MemberDTO loginpost(LoginDTO login) {		
		return session.selectOne(NS+".loginpost", login);
	}
	
	@Override
	public int idChk(MemberDTO dto) {
		// TODO Auto-generated method stub
		return session.selectOne(NS + ".idChk", dto);
	}



	@Override
	public int selectGrno(String auth) {
		// TODO Auto-generated method stub
	
		return session.selectOne(NS+".selectGrno", auth);
	}



	@Override
	public MemberDTO selectMeberDTO(String id) {
		// TODO Auto-generated method stub
		return session.selectOne(NS+".selectMeberDTO", id);
	}



	@Override
	public int updateGrno(MemberDTO originalDTO) {
		// TODO Auto-generated method stub
		return session.update(NS+".updateGrno", originalDTO);
	}

}
