package com.m520it.day2.smis.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.m520it.day2.smis.handler.IResultSetHandler;

public class JdbcTemplate {
	private JdbcTemplate() {}
	
	/**
	 * DML操作模板
	 * @param sql 调用者要执行的SQL
	 * @param params 占位符参数的值
	 */
	public static void update(String sql, Object... params) {
		try (
				Connection conn = JdbcUtil.getConn();
				PreparedStatement ps = conn.prepareStatement(sql);
		) {
			//设置占位符参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * DQL操作模板
	 * @param sql 调用者要执行的SQL
	 * @param params 占位符参数的值
	 * @return 查询到的对象集合
	 */
	public static <T> T query(String sql, IResultSetHandler<T> rsh
			, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			//设置占位符参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			//需要有一个处理结果集的方法
			return rsh.handle(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			 JdbcUtil.close(conn, ps, rs);
		}
	}
}
