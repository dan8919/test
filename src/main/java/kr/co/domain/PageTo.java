package kr.co.domain;

import java.util.List;

public class PageTo<T> { //제네릭 타입으로
	private int curPage = 1; //최근페이지 = 첫페이지
	private int perPage = 12; //한 페이지당 12개의 게시글
	private int pageLine = 10; //페이지당 10개의 페이지링크
	private int amount; //게시글의 총개수 --정보넘겨주기 위한 용도
	
	//--종속변수--//
	
	private int totalPage; //페이지의 총개수
	
	//startNum, endNum은 mybatis에서 지원해줌 (기존 rownum활용)
	private int startNum; //페이지의 시작하는 숫자(게시글번호)
	private int endNum; //페이지의 끝나는 숫자(게시글번호)
	
	private int beginPageNum; //웹화면상 페이지의 시작번호(페이지)
	private int stopPageNum; //웹화면상 페이지의 끝번호(페이지)
	
//	private List<BoardVO> list; //페이지리스트
	private List<T> list; //--확장성
	
	public PageTo() {
		executeAll();
	}

	//사용자생성자는 curPage만
	public PageTo(int curPage) {
		super();
		this.curPage = curPage;
		executeAll();
	}
	
	//get/set은 전체 생성
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		executeAll();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executeAll();
	}

	public int getPageLine() {
		return pageLine;
	}

	public void setPageLine(int pageLine) {
		this.pageLine = pageLine;
		executeAll();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		executeAll();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getStopPageNum() {
		return stopPageNum;
	}

	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	

	
	private void executeAll() {
		totalPage = (amount - 1) / perPage + 1;
		
		startNum = (curPage - 1) * perPage + 1;
		endNum = curPage * perPage;
		if (endNum > amount) {
			endNum = amount;
//			└총게시글의 수가 27이면
//				endNum = 27
		}
		
		beginPageNum = ((curPage - 1)/pageLine) * pageLine + 1;
		
		stopPageNum = beginPageNum + (pageLine - 1);
		if (stopPageNum > totalPage) {
			stopPageNum = totalPage;
//			└stopPageNum = 100 일때
//			 totalPage = 108 이면,
//				101 ~ 108 --이렇게 출력
		}
	}
}

