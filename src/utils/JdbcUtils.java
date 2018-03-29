package utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String mysqlUrl;
    private static String mysqlUsername;
    private static String mysqlPassword;

    //静态代码块注册驱动
    static {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = JdbcUtils.class.getClassLoader();
            InputStream stream = classLoader.getResourceAsStream("db.properties");
            properties.load(stream);
            stream.close();
            //读取配置文件中数据
            Class.forName(properties.getProperty("driverClassName"));
            mysqlUrl = properties.getProperty("url");
            mysqlUsername = properties.getProperty("username");
            mysqlPassword = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取mysql连接
    public static Connection getMysqlConnect() {
        try {
            return DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
