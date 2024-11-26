package customer;

import DBUtil.DBUtil;
import entity.CustomerDTO;

import java.sql.*;

public class CustomerRepository {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // customer_id 컬럼을 제외하고 삽입하는 SQL 문
    String sql_insert = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";
    String sql_selectById = "select * from customer where EMAIL = ?";
    String sql_delete = "delete from customer where EMAIL = ?";
    String sql_update = """
            UPDATE CUSTOMER SET
                USERNAME = ?,
                PASSWORD = ?,
                NICKNAME = ?,
                area_id = ?
            WHERE EMAIL =?
            """;

    public int createMember(CustomerDTO customerDTO) {
        int result = 0;
        conn = DBUtil.getConnection();
        try {
            // customer_id를 제외한 컬럼만 지정
            st = conn.prepareStatement(sql_insert);
            st.setString(1, customerDTO.getEmail());
            st.setString(2, customerDTO.getName());
            st.setString(3, customerDTO.getPassword());
            st.setString(4, customerDTO.getNickName());
            st.setInt(5, customerDTO.getLocation());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public CustomerDTO selectByEmail(String email) {
        CustomerDTO customerDTO = new CustomerDTO();
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_selectById);
            st.setString(1, email);
            rs = st.executeQuery();
            while (rs.next()) {
                customerDTO = makeMember(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return customerDTO;
    }

    public int deleteMember(String email) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_delete);
            st.setString(1, email);
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public int update(CustomerDTO customerDTO) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_update);
//            st.setString(1, customerDTO.getName());
//            st.setString(2, customerDTO.getPassword());
//            st.setString(3, customerDTO.getNickName());
//            st.setInt(4, customerDTO.getLocation());
//            st.setInt(5, customerDTO.get());

            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    private CustomerDTO makeMember(ResultSet rs) throws SQLException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(rs.getString("Username"));
        customerDTO.setPassword(rs.getString("Password"));
        customerDTO.setEmail(rs.getString("Email"));
        customerDTO.setNickName(rs.getString("Nickname"));
        customerDTO.setLocation(rs.getInt("Area_id"));
        return customerDTO;
    }
}
