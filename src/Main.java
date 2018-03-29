import dao.IStudentDAO;
import dao.impl.StudentDAOImpl;
import domain.Student;

import java.util.List;

class Main {
    public static void main(String[] args)throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

//        String sql = "INSERT INTO `t_student` ( `name`, `age`) VALUES ( 'q22q', '1')";
//        Statement statement = connection.createStatement();
//        int execute = statement.executeUpdate(sql);
//
//        System.out.print(execute);
//        statement.close();
//        connection.close();


//        try(
//
//                Connection connection = JdbcUtils.getMysqlConnect()
//                ){
//            String sql = "SELECT * FROM kaka WHERE id=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setLong(1, 14);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.print(resultSet.getInt("id"));
//                System.out.print(resultSet.getString("name"));
//                System.out.print("\n");
//
//            }
//
//
//        }
        //

        IStudentDAO iStudentDAO = new StudentDAOImpl();
        List<Student> list = iStudentDAO.list();
        for (Student it: list
             ) {
            System.out.print(it);
            System.out.print("\n");

        }


    }

//    static void ceshi(int ...a) {
//        for (int i:a
//             ) {
//            System.out.print(i);
//
//        }
//
//
//    }

}
