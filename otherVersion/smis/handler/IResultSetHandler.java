package com.m520it.day2.smis.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集处理器的规范
 * @author xmg
 * @param T 表示结果集处理后的返回类型
 */
public interface IResultSetHandler<T> {
	/**
	 * 处理结果集的方法
	 * @param rs 需要被处理的结果集对象
	 * @return 处理后的对象
	 * @throws SQLException
	 */
	T handle(ResultSet rs) throws SQLException;
}
