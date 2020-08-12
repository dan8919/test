package kr.co.domain;

import java.io.Serializable;

public class MemberDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pw;
	private String uname;
	private int phone;
	private String addr;
	private int grno;
	private String authority;


	

	public MemberDTO(String id, String pw, String uname, int phone, String addr, int grno, String authority) {
		super();
		this.id = id;
		this.pw = pw;
		this.uname = uname;
		this.phone = phone;
		this.addr = addr;
		this.grno = grno;
		this.authority = authority;
	}




	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}


	public MemberDTO(String id, String pw, String uname, int phone, String addr, String authority) {
		super();
		this.id = id;
		this.pw = pw;
		this.uname = uname;
		this.phone = phone;
		this.addr = addr;
		this.authority = authority;
	}

	public int getGrno() {
		return grno;
	}


	public void setGrno(int grno) {
		this.grno = grno;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
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


	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", uname=" + uname + ", phone=" + phone + ", addr=" + addr
				+ ", authority=" + authority + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		return true;
	}

	

	
}
