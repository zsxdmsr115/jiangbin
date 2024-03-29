package com.yc.drp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtil {

	/**
	 * ȡ��Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		Connection conn = null;
		try {
			JdbcConfig jdbcConfig = XmlConfigReader.getInstance().getJdbcConfig();
			Class.forName(jdbcConfig.getDriverName());
			conn = DriverManager.getConnection(jdbcConfig.getUrl(), jdbcConfig.getUserName(), jdbcConfig.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet res) {
		close(conn);
		close(pstmt);
		close(res);
	}

	public static void main(String[] args) {
		System.out.println(DbUtil.getConnection());
	}
}
