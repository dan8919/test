package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Inject
	private MemberDAO memberDao;
//			�뵒�씤�꽣�럹�씠�뒪 援ы쁽

	@Override
	public void insert(MemberDTO dto) {
		memberDao.insert(dto);
		
	}

	@Override
	public List<MemberDTO> list() {
		// TODO Auto-generated method stub
		return memberDao.list();
	}

	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return memberDao.read(id);
	}

	@Override
	public MemberDTO updateui(String id) {
		// TODO Auto-generated method stub
		return memberDao.updateui(id);
	}

	@Override
	public void update(MemberDTO dto) {
		// TODO Auto-generated method stub
		memberDao.update(dto);
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		memberDao.delete(id);
		
	}

	@Override
	public MemberDTO loginpost(LoginDTO login) {
		return memberDao.loginpost(login);
	}
	
	@Override
	public int idChk(MemberDTO dto) {
		// TODO Auto-generated method stub
		return memberDao.idChk(dto);
	}



	@Override
	public int selectGrno(String auth) {
		// TODO Auto-generated method stub
		return memberDao.selectGrno(auth);
	}

	@Override
	public MemberDTO selectMeberDTO(String id) {
		// TODO Auto-generated method stub
		return memberDao.selectMeberDTO(id);
	}

	@Override
	public int updateGrno(MemberDTO originalDTO) {
		// TODO Auto-generated method stub
		return memberDao.updateGrno(originalDTO);
	}
}
