package util;


import java.sql.*;

/**
 * 获取数据库连接
 */
public class DbUtil {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dazi2?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";


    // static final String DB_URL = "jdbc:mysql://localhost:3306/dazi?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    static final String USER = "root";
    static final String PASS = "3847";

    /**
     * 获取数据库连接
     * @return null表示连接失败
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void release(ResultSet rs, Statement st, Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = DbUtil.getConnection();
        System.out.println(connection);
    }

}
