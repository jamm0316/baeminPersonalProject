package DBUtil;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() {
//        String url = "jdbc:oracle:thin:@localhost:1521:FREE";
        String url = "jdbc:oracle:thin:192.168.0.35:1521:FREE";
        String userId = "delivery", userPassword = "pass1234";
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url, userId, userPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (st != null) {
                st.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
