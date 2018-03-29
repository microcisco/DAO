package com.m520it.day2.smis.test;

import java.util.List;

import org.junit.Test;

import com.m520it.day2.smis.dao.IStudentDAO;
import com.m520it.day2.smis.dao.impl.StudentDAOImpl;
import com.m520it.day2.smis.domain.Student;
import com.m520it.day2.smis.handler.impl.ScalarHandler;
import com.m520it.day2.smis.util.JdbcTemplate;

//学生DAO的测试类
public class StudentDAOTest {

	private IStudentDAO dao = new StudentDAOImpl();
	
	@Test
	public void testSave() {
		Student stu = new Student(null, "王尼玛", 28);
		dao.save(stu);
	}

	@Test
	public void testDelete() {
		dao.delete(208L);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student(207L, "admin", 20);
		dao.update(stu);
	}

	@Test
	public void testGet() {
		Student stu = dao.get(207L);
		System.out.println(stu);
	}

	@Test
	public void testList() {
		List<Student> list = dao.list();
		for (Student stu : list) {
			System.out.println(stu);
		}
	}

	@Test
	public void testCount() {
		String sql = "SELECT COUNT(id) FROM t_student";
		Long count = JdbcTemplate.query(sql, new ScalarHandler());
		/*Long count = JdbcTemplate.query(sql, new IResultSetHandler<Long>() {
			public Long handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getLong(1);
				}
				return 0L;
			}
		});*/
		System.out.println(count);
	}

}
