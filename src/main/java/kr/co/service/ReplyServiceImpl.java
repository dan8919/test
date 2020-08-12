package kr.co.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.ReplyVO;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO rDao;

	@Autowired
	private BoardDAO bDao;	
	

	@Transactional
	@Override
	public int insert(ReplyVO vo) {
		bDao.updateRepCnt(vo.getBno(), 1);
		return rDao.insert(vo);
	}

	@Override
	public List<ReplyVO> list(int bno) {
		return rDao.list(bno);
	}

	@Override
	public int update(ReplyVO vo) {
		return rDao.update(vo);
	}

	@Transactional
	@Override
	public int delete(int rno) {
		ReplyVO vo = new ReplyVO();
		bDao.updateRepCnt(vo.getBno(), -1); //삭제반영 오류
		return rDao.delete(rno);
	}
	
	
	
}
