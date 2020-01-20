package co.micol.appTest.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.micol.appTest.dto.UserDto;
import co.micol.appTest.service.Service;

public class UserServiceImpl extends Service {
	private PreparedStatement psmt;
	private ResultSet rs;
	private List<UserDto> list;
	private UserDto dto;
	
	private final String SELECT_ALL = "SELECT USER_ID, USER_LEVEL, TO_CHAR(ENTER_DATE, 'YYYY-MM-DD HH24:MI:SS') ENTER_DATE FROM USERT";
	private final String SELECT = "SELECT * FROM USERT WHERE USER_ID = ?";
	private final String INSERT = "INSERT INTO USERT(USER_ID, USER_PW, ENTER_DATE) VALUES(?,?,?)";
	private final String DELETE = "DELETE FROM USERT WHERE USER_ID = ?";
	
	@Override
	public List<UserDto> selectUserAll() {
		list = new ArrayList<UserDto>();
		try {
			psmt = conn.prepareStatement(SELECT_ALL);
			rs = psmt.executeQuery();
			while(rs.next()) {
				dto = new UserDto();
				dto.setUser_id(rs.getString("user_id"));
//				dto.setUser_pw(rs.getString("user_pw")); //수정
				dto.setUser_level(rs.getString("user_level"));
				dto.setEnter_date(rs.getTimestamp("enter_date"));
				list.add(dto); //수정
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UserDto select(UserDto dto) {
		try {
			psmt = conn.prepareStatement(SELECT);
			psmt.setString(1, dto.getUser_id());
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setEnter_date(rs.getTimestamp("enter_date"));
				dto.setUser_level(rs.getString("user_level"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int insert(UserDto dto) {
		Timestamp timestamp = java.sql.Timestamp.valueOf(LocalDateTime.now()); //현재시간 구하기
		int n = 0;
		try {
			psmt = conn.prepareStatement(INSERT);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getUser_pw());
			psmt.setTimestamp(3, timestamp);  //DB date type에 년월일 시분초를 넣기위해서.
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int update(UserDto dto, String str) {
		int n = 0;
		String UPDATE;
		if(str.equals("password"))
			UPDATE = "UPDATE USERT SET USER_PW = ? WHERE USER_ID = ?";
		else 
			UPDATE = "UPDATE USERT SET USER_LEVEL = ? WHERE USER_ID = ?";
		
		try {
			psmt = conn.prepareStatement(UPDATE);
			if(str.equals("password"))
				psmt.setString(1, dto.getUser_pw());
			else
				psmt.setString(1, dto.getUser_level());
			psmt.setString(2, dto.getUser_id());
			
			n = psmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(UserDto dto) {
		int n = 0;
		try {
			psmt = conn.prepareStatement(DELETE);
			psmt.setString(1, dto.getUser_id());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

}
