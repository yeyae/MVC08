package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB�� ������ �������ִ� ģ��
public class ConnectionProvider {
	/*
	 * ���ῡ �ʿ��� ����
	 * �����ͺ��̽� URL
	 * �����ͺ��̽� ����� �̸�
	 * �����ͺ��̽� ����� ��й�ȣ
	 */
	// ����Ŭ�����ͺ��̽�
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "minseok";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
