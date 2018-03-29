package com.m520it.day2.smis.test;

import java.util.List;

import org.junit.Test;

import com.m520it.day2.smis.domain.Product;
import com.m520it.day2.smis.handler.impl.BeanHandler;
import com.m520it.day2.smis.handler.impl.BeanListHandler;
import com.m520it.day2.smis.util.JdbcTemplate;

public class ProductTest {
	@Test
	public void testGet() throws Exception {
		String sql = "SELECT * FROM product WHERE id = ?";
		Product p = JdbcTemplate.query(sql, new BeanHandler<>(Product.class), 16L);
		System.out.println(p);
	}

	@Test
	public void testList() throws Exception {
		String sql = "SELECT * FROM product";
		List<Product> list = JdbcTemplate.query(sql, new BeanListHandler<>(Product.class));
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
}
