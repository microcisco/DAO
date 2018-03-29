package dao.impl;

import dao.IStudentDAO;
import domain.Student;
import utils.JdbcTemplate;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    @Override
    public void save(Student stu) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Student stu) {
        String sql = "INSERT INTO `t_student` (`name`, `age`) VALUES (?, ?)";
        JdbcTemplate.update(sql, stu.getName(), stu.getAge());

    }

    @Override
    public Student get(Long id) {
        return null;
    }

    @Override
    public List<Student> list() {
        String sql = "SELECT * FROM `t_student`  WHERE `id`>?;";
        return JdbcTemplate.query(sql, Student.class, 1);

    }
}
