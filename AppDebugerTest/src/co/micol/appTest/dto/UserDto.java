package co.micol.appTest.dto;


import java.sql.Timestamp;

public class UserDto {
	private String user_id;
	private String user_pw;
	private Timestamp enter_date; //데이터타입을 시분초 관리하기 위해
	private String user_level;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public Timestamp getEnter_date() {
		return enter_date;
	}

	public void setEnter_date(Timestamp enter_date) {
		this.enter_date = enter_date;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

//	public Date getEnter_date() {
//		return enter_date;
//	}

//	public void setEnter_date(Date enter_date) {
//		this.enter_date = enter_date;
//	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String toString() {
		System.out.print(user_id + "  ");
		System.out.print(user_level + "  ");
		System.out.println(enter_date + "  ");		
		return null;
	}
}
