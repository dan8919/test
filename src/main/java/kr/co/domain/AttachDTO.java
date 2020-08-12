package kr.co.domain;

import java.io.Serializable;

public class AttachDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uno;
	private int bno;
	private String fullName;
	
	public AttachDTO() {
		// TODO Auto-generated constructor stub
	}

	public AttachDTO(int uno, int bno, String fullName) {
		super();
		this.uno = uno;
		this.bno = bno;
		this.fullName = fullName;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AttachDTO [uno=" + uno + ", bno=" + bno + ", fullName=" + fullName + "]";
	}
	
	

}
