package com.m520it.day2.smis.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.m520it.day2.smis.dao.IStudentDAO;
import com.m520it.day2.smis.domain.Student;
import com.m520it.day2.smis.handler.IResultSetHandler;
import com.m520it.day2.smis.handler.impl.BeanHandler;
import com.m520it.day2.smis.handler.impl.BeanListHandler;
import com.m520it.day2.smis.util.JdbcTemplate;

@SuppressWarnings("all")
public class StudentDAOImpl implements IStudentDAO {

	public void save(Student stu) {
		String sql = "INSERT t_student(name,age) VALUES(?,?)";
		Object[] params = { stu.getName(), stu.getAge() };
		JdbcTemplate.update(sql, params);
	}

	public void delete(Long id) {
		String sql = "DELETE FROM t_student WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	public void update(Student stu) {
		String sql = "UPDATE t_student SET name = ?, age = ? WHERE id = ?";
		Object[] params = { stu.getName(), stu.getAge(), stu.getId() };
		JdbcTemplate.update(sql, params);
	}

	public Student get(Long id) {
		String sql = "SELECT * FROM t_student WHERE id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
	}

	public List<Student> list() {
		String sql = "SELECT * FROM t_student";
		return JdbcTemplate.query(sql, new BeanListHandler<>(Student.class));
	}

	private class StudentHandler 
			implements IResultSetHandler<List<Student>> {
		public List<Student> handle(ResultSet rs) {
			try (
					ResultSet r = rs;
			) {
				List<Student> list = new ArrayList<>();
				while (r.next()) {

					Long id = r.getLong("id");
					String name = r.getString("name");
					Integer age = r.getInt("age");
					// 把查询的结果封装成对象
					Student stu = new Student(id, name, age);
					list.add(stu);
				}
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
