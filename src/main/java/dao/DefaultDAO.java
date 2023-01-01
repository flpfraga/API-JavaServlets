package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultDAO {

	public DefaultDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
		e.printStackTrace();
		}
	}
	protected Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/servlets-test";
		Connection conn = DriverManager.getConnection(url, "root", "admin123");
		return conn;
	}
	
	public static void main (String[] args) throws SQLException {
		DefaultDAO dd = new DefaultDAO();
		try {
			Connection conn = dd.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		
	}
}
