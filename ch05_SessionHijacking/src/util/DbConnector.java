package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {	
	private static DbConnector single = null;

	private DbConnector() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DbConnector getInstance() {
		if (single == null) {
			single = new DbConnector();
		}
		return single;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		
		//연결작업
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "secure";
		String pwd = "1111";
		
		try {
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
