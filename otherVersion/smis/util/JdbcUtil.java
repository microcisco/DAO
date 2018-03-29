package com.m520it.day2.smis.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

//jdbc操作的工具类
public class JdbcUtil {
	private JdbcUtil() {}
	
	private static String url;
	private static String username;
	private static String password;
	
	//注册驱动只需要在程序驱动的使用注册1次即可,使用静态代码块
	static {
		try {
			Properties p = new Properties();
			//加载配置文件信息
			ClassLoader loader = JdbcUtil.class.getClassLoader();
			//在classpath路径中找db.properties
			InputStream in = loader.getResourceAsStream("db.properties");
			//从流中获取属性文件的内容
			p.load(in);
			in.close();
			
			Class.forName(p.getProperty("driverClassName"));
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
