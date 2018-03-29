package dao;

import domain.Student;

import java.util.List;

public interface IStudentDAO {
    /**
     * 保存一个学生对象
     * @param stu 要保存的学生
     */
    void save(Student stu);

    /**
     * 根据ID删除一个学生
     * @param id 学生的ID
     */
    void delete(Long id);

    /**
     * 更新一个学生的信息
     * 注意:该学生对象必须有ID值表被更新的学生的ID
     * @param stu 更新后的学生对象
     */
    void update(Student stu);

    /**
     * 根据ID查询一个学生对象
     * @param id 被查询的学生的ID
     * @return 学生对象,没有查到返回null
     */
    Student get(Long id);

    /**
     * 查询所有的学生对象
     * @return 学生对象的集合,没有数据则返回空集合
     */
    List<Student> list();
}
