package DBUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {

//    public static Connection getConnection() {
//        Context initContext = null;
//        Connection conn = null;
//        try {
//            initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
//            conn = ds.getConnection();
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return conn;
//    }

    public static Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:FREE";
//        String url = "jdbc:oracle:thin:192.168.0.35:1521:FREE";
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
