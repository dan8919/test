package kr.co.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //실행시간 체크를 위한 트랜잭션 활용
public class AOPTestServiceImpl implements AOPTestService {

	@Override
	public void aoptest() {
		//aop 적용 안된 코드
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}

	@Override
	public void aoptest2() {
		//aop 테스트
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}		
	}

	@Override
	public void aoptest3() {
		//aop로 실행시간 체크
		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
		}
		
	}


}
