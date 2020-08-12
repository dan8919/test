package kr.co.domain;

import java.io.Serializable;

public class ClothesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private int gno;
	private int cno;
	private String gname;
	private int price;
	private int rno;

	public ClothesDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public int getRno() {
		return rno;
	}


	public void setRno(int rno) {
		this.rno = rno;
	}


	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ClothesDTO [gno=" + gno + ", cno=" + cno + ", gname=" + gname + ", price=" + price + ", rno=" + rno
				+ "]";
	}
	


	public ClothesDTO(int gno, int cno, String gname, int price, int rno) {
		super();
		this.gno = gno;
		this.cno = cno;
		this.gname = gname;
		this.price = price;
		this.rno = rno;
	}
	
	
	

}
