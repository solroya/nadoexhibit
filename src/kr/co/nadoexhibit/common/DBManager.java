package kr.co.nadoexhibit.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {

	private static Connection conn;
	private final static String DRIVER = "oracle.jdbc.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521/xe";
	private final static String USER = "nado";
	private final static String PASS = "0000";
	
//	private final static String URL = "jdbc:oracle:thin:@192.168.0.38:1521/xe";
//	private final static String USER = "kch";
//	private final static String PASS = "java0107";

	private DBManager() {
	}

	public static Connection getConnection() {
		conn = null;
		if (conn == null) {
			try {
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USER, PASS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
