package store;

import DBUtil.DBUtil;
import entity.CustomerDTO;
import entity.StoreDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // store_id 컬럼을 제외하고 삽입하는 SQL 문
    String sql_insert = "INSERT INTO store (NAME, AREA_ID, OWNER_EMAIL, CATEGORY_ID) values (?, ?, ?, ?)";
    String sql_selectByEmail = "select * from STORE where OWNER_EMAIL = ?";
    String sql_selectByCategory = "select * from store where CATEGORY_ID = ?";
    String sql_selectByStoreId = "select * from store where STORE_ID = ?";
    String sql_deleteStore = "delete from store where store_id = ?";
    String sql_update = """
            UPDATE STORE SET
                NAME = ?,
                AREA_ID = ?,
                CATEGORY_ID = ?
            WHERE STORE_ID =?
                """;

    public int createStore(StoreDTO storeDTO) {
        int result = 0;
        conn = DBUtil.getConnection();
        try {
            // store_id를 제외한 컬럼만 지정
            st = conn.prepareStatement(sql_insert);
            st.setString(1, storeDTO.getName());
            st.setInt(2, storeDTO.getArea_id());
            st.setString(3, storeDTO.getOwnerEmail());
            st.setInt(4, storeDTO.getCategory());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public List<StoreDTO> selectByEmail(String email) {
        StoreDTO storeDTO = new StoreDTO();
        conn = DBUtil.getConnection();
        List<StoreDTO> storeList = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql_selectByEmail);
            st.setString(1, email);
            rs = st.executeQuery();
            while (rs.next()) {
                storeDTO = makeStore(rs);
                storeList.add(storeDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return storeList;
    }

    public List<StoreDTO> selectByCategory(int category) {
        StoreDTO storeDTO = new StoreDTO();
        conn = DBUtil.getConnection();
        List<StoreDTO> storeList = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql_selectByCategory);
            st.setInt(1, category);
            rs = st.executeQuery();
            while (rs.next()) {
                storeDTO = makeStore(rs);
                storeList.add(storeDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return storeList;
    }

    private StoreDTO makeStore(ResultSet rs) throws SQLException {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(rs.getInt("STORE_ID"));
        storeDTO.setName(rs.getString("NAME"));
        storeDTO.setArea_id(rs.getInt("AREA_ID"));
        storeDTO.setOwnerEmail(rs.getString("OWNER_EMAIL"));
        storeDTO.setCategory(rs.getInt("CATEGORY_ID"));
        return storeDTO;
    }

    public StoreDTO selectByStoreId(int storeId) {
        StoreDTO storeDTO = new StoreDTO();
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_selectByStoreId);
            st.setInt(1, storeId);
            rs = st.executeQuery();
            while (rs.next()) {
                storeDTO = makeStore(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return storeDTO;
    }

    public int deleteStore(int storeId) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_deleteStore);
            st.setInt(1, storeId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public int updateStore(StoreDTO storeDTO) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_update);
            st.setString(1, storeDTO.getName());
            st.setInt(2, storeDTO.getArea_id());
            st.setInt(3, storeDTO.getCategory());
            st.setInt(4, storeDTO.getId());

            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }
}