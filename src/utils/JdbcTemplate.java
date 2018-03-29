package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public static void update(String sql, Object... params) {
        try (
                Connection mysqlConnect = JdbcUtils.getMysqlConnect();
                PreparedStatement prepareStatement = mysqlConnect.prepareStatement(sql)
        ) {
            //设置占位符参数
            for (int i = 0; i < params.length; ++i) {
                prepareStatement.setObject(i + 1, params[i]);

            }
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> query(String sql, Class<T> clzz, Object... params) {
        ArrayList<T> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getMysqlConnect();
            preparedStatement = connection.prepareStatement(sql);
            //设置占位符参数
            for (int i = 0; i < params.length; ++i) {
                preparedStatement.setObject(i + 1, params[i]);

            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T instance = clzz.newInstance();
                BeanInfo beanInfo = Introspector.getBeanInfo(clzz, Object.class);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor it : propertyDescriptors
                        ) {
                    //处理字段不存在
                    try {
                        String name = it.getName();
                        Object value = resultSet.getObject(name);
                        it.getWriteMethod().invoke(instance, value);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                list.add(instance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

}
