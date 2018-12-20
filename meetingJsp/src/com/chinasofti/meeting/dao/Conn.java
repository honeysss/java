package com.chinasofti.meeting.dao;

import java.sql.Connection;
import java.sql.DriverManager;
//ÐÂµÄ
public class Conn {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/meeting";
	private static String name = "root";
	private static String psw = "";
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, psw);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
