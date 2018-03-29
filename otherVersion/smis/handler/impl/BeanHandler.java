package com.m520it.day2.smis.handler.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

import com.m520it.day2.smis.handler.IResultSetHandler;

public class BeanHandler<T> implements IResultSetHandler<T> {
	private Class<T> clz;//表示结果集中的数据应该处理成怎样的对象
	
	public BeanHandler(Class<T> clz) {
		this.clz = clz;
	}
	
	public T handle(ResultSet rs) {
		T obj = null;
		try (
				ResultSet r = rs;
		) {
			if (r.next()) {
				obj = clz.newInstance();
				BeanInfo info = Introspector.getBeanInfo(clz, Object.class);
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				for (PropertyDescriptor pd : pds) {
					//获取属性的名称和列名相同
					String name = pd.getName();
					//从结果集中获取列的值
					Object value = r.getObject(name);
					//把值通过属性的写的方法给obj对象
					pd.getWriteMethod().invoke(obj, value);
				}
			}
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
