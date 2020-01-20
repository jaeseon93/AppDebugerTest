package co.micol.appTest.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import co.micol.appTest.dto.UserDto;

public abstract class Service {
	public Connection conn;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	public Service() {
		getConnection();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getConnection() {
		Properties properties = new Properties();
		try {
			Reader reader = new FileReader("config/db.properties");
			properties.load(reader);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public abstract List<UserDto> selectUserAll();
	public abstract UserDto select(UserDto dto);
	public abstract int insert(UserDto dto);
	public abstract int update(UserDto dto, String str);
	public abstract int delete(UserDto dto);	
}
