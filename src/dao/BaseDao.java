package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    /* 加载驱动包 */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* 获得连接的方法 */
    public static Connection getconn() {
        //数据库服务器路径，bookSys为数据库名
//        String url = "jdbc:mysql://localhost:3306/mysql";
        String url = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 返回连接对象
        return conn;
    }

    /* 关闭连接的方法 */
    public static void closeAll(Connection conn, PreparedStatement ps,
                                ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = getconn();
        System.out.println(conn);
    }
}
