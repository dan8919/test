package kr.co.persistence;

import java.io.Serializable;

public class GrantsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int grno;
	private String authority;
	
	
	public GrantsDTO() {
		// TODO Auto-generated constructor stub
	}


	public GrantsDTO(int grno, String authority) {
		super();
		this.grno = grno;
		this.authority = authority;
	}


	public int getGrno() {
		return grno;
	}


	public void setGrno(int grno) {
		this.grno = grno;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
