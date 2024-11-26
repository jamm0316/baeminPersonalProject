package owner;

import DBUtil.DBUtil;
import entity.CustomerDTO;
import entity.OwnerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRepository {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // customer_id 컬럼을 제외하고 삽입하는 SQL 문
    String sql_insert = "INSERT INTO owner VALUES (?, ?, ?)";
    String sql_selectById = "select * from owner where EMAIL = ?";
    String sql_delete = "delete from owner where email = ?";
    String sql_update = """
            UPDATE owner SET
                NAME = ?,
                PASSWORD = ?,
            WHERE EMAIL =?
                """;

    public int createOwner(OwnerDTO ownerDTO) {
        int result = 0;
        conn = DBUtil.getConnection();
        try {
            // customer_id를 제외한 컬럼만 지정
            st = conn.prepareStatement(sql_insert);
            st.setString(1, ownerDTO.getEmail());
            st.setString(2, ownerDTO.getName());
            st.setString(3, ownerDTO.getPassword());

            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public OwnerDTO selectByEmail(String email) {
        OwnerDTO ownerDTO = new OwnerDTO();
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_selectById);
            st.setString(1, email);
            rs = st.executeQuery();
            while (rs.next()) {
                ownerDTO = makeMember(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return ownerDTO;
    }

    public int delete(int memberId) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_delete);
            st.setInt(1, memberId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public int update(CustomerDTO memberDTO) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_update);
            st.setString(1, memberDTO.getName());
            st.setString(2, memberDTO.getPassword());
            st.setString(3, memberDTO.getNickName());
            st.setInt(4, memberDTO.getLocation());
//            st.setInt(5, memberDTO.getId());

            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    private OwnerDTO makeMember(ResultSet rs) throws SQLException {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setEmail(rs.getString("EMAIL"));
        ownerDTO.setName(rs.getString("NAME"));
        ownerDTO.setPassword(rs.getString("PASSWORD"));
        return ownerDTO;
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
}

