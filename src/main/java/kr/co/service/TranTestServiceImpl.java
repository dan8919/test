package kr.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;

@Service
@Transactional
public class TranTestServiceImpl implements TranTestService {

	@Autowired
	private BoardDAO bDao;
	
	@Override
	public void insertNupdate1(BoardVO vo) {
		//트랜잭션 안 한 경우
		
		bDao.insert(vo); //인서트 실행
		
		System.out.println(4/0); //에러발생시키기
		
		bDao.update(vo); //업데이트 실행X
		
	}

}
