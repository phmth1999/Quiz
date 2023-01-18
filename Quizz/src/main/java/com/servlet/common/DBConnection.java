package com.servlet.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection connectionDB() throws Exception {
		Connection conn = null;
		try {
			Class.forName(Constant.DB_DRIVER);
			String dbURL = Constant.DB_URL;
			String dbUser = Constant.DB_USERNAME;
			String dbPassword = Constant.DB_PASSWORD;
			conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnectionDB(Connection conn) throws Exception {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollbackTransaction(Connection conn) throws Exception {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
