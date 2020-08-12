package kr.co.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

public class BoardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bno;
	private int cno;
	private int gno;
	private String title;
	private String regDate;
	private String[] files; //업로드 파일 전부
	private String cname;
	private String gname;
	private String fullName;
	private int price;
	
	//files 추가된 사용자 생성자
	

	public BoardVO() {
		// TODO Auto-generated constructor stub
	}

	public BoardVO(int bno, int cno, int gno, String title, String regDate, String[] files, String cname, String gname, int price, String fullName) {
		super();
		this.bno = bno;
		this.cno = cno;
		this.gno = gno;
		this.title = title;
		this.regDate = regDate;
		this.files = files;
		this.cname = cname;
		this.gname = gname;
		this.price = price;
		this.fullName = fullName;
	}
	
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BoardVO [ bno=" + bno + ", cname=" + cname + ", gname=" + gname + ", price=" + price + ", cno=" + cno + ", gno=" + gno + ", title=" + title + ", fullName=" + fullName + ", regDate=" + regDate
				+ ", files=" + Arrays.toString(files) + "]";
	}

	
	

}
