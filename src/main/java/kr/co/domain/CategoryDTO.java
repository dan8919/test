package kr.co.domain;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cno;
	private String cname;
	
	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CategoryDTO(int cno, String cname) {
		super();
		this.cno = cno;
		this.cname = cname;
	}

	@Override
	public String toString() {
		return "CategoryDTO [cno=" + cno + ", cname=" + cname + "]";
	}
	
	

}
