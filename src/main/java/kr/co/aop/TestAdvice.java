package kr.co.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component //TestAdvice 객체를 만들기위한 어노테이션
@Aspect //app를 위한 advice임을 지칭
public class TestAdvice {
	
	@Around("execution(* kr.co.service.AOPTestService*.*(..))")
	public void duration(ProceedingJoinPoint pjp) throws Throwable {
//															└Exception보다 상위
		long start = System.currentTimeMillis();
		
		pjp.proceed(); //회신코드 (서비스Impl에 있는 메소드 for문)
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}

	@After("execution(* kr.co.service.AOPTestService*.*(..))")
	public void end() {
		System.out.println("****************************");
	}
	
	//Before Advice로 메소드(joinpoints) 지정
	@Before("execution(* kr.co.service.AOPTestService*.*(..))")
	public void start(JoinPoint jp) {
		System.out.println("::::::::::::::::::::::::::::");
		System.out.println(jp.getKind()); //어드바이스 타입 알아볼수있음
		System.out.println(jp.getSignature()); //어떤 객체(target)의 어떤 메소드(joinpoints)가 실행되는지
		System.out.println(jp.getTarget()); //객체(target) 출력
		System.out.println("::::::::::::::::::::::::::::");
	}
}
