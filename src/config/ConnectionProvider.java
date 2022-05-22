package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB와 연결을 제공해주는 친구
public class ConnectionProvider {
	/*
	 * 연결에 필요한 정보
	 * 데이터베이스 URL
	 * 데이터베이스 사용자 이름
	 * 데이터베이스 사용자 비밀번호
	 */
	// 오라클데이터베이스
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "minseok";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
