package food;

import DBUtil.DBUtil;
import entity.CustomerDTO;
import entity.FoodDTO;
import entity.StoreDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // food_id 컬럼을 제외하고 삽입하는 SQL 문
    String sql_insert = "INSERT INTO food (NAME, PRICE, MAX_COOKING_TIME, STORE_ID)  VALUES (?, ?, ?, ?)";
    String sql_selectByStoreId = "select * from food where STORE_ID = ?";
    String sql_selectByFoodId = "select * from food where FOOD_ID = ?";
    String sql_update = "UPDATE food SET NAME = ?, PRICE = ?, MAX_COOKING_TIME = ? WHERE FOOD_ID = ?";
    String sql_delete = "delete from food where food_id = ?";

    public int createFood(FoodDTO foodDTO) {
        int result = 0;
        conn = DBUtil.getConnection();
        try {
            // food_id를 제외한 컬럼만 지정
            st = conn.prepareStatement(sql_insert);
            st.setString(1, foodDTO.getName());
            st.setInt(2, foodDTO.getPrice());
            st.setInt(3, foodDTO.getMaxCookingTime());
            st.setInt(4, foodDTO.getStoreId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public List<FoodDTO> selectByStoreId(int storeId) {
        FoodDTO foodDTO = new FoodDTO();
        conn = DBUtil.getConnection();
        List<FoodDTO> foodList = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql_selectByStoreId);
            st.setInt(1, storeId);
            rs = st.executeQuery();
            while (rs.next()) {
                foodDTO = makeFood(rs);
                foodList.add(foodDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return foodList;
    }

    private FoodDTO makeFood(ResultSet rs) throws SQLException {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodId(rs.getInt("FOOD_ID"));
        foodDTO.setName(rs.getString("NAME"));
        foodDTO.setPrice(rs.getInt("PRICE"));
        foodDTO.setMaxCookingTime(rs.getInt("MAX_COOKING_TIME"));
        foodDTO.setStoreId(rs.getInt("STORE_ID"));
        return foodDTO;
    }

    public FoodDTO selectByFoodId(int foodId) {
        FoodDTO foodDTO = new FoodDTO();
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_selectByFoodId);
            st.setInt(1, foodId);
            rs = st.executeQuery();
            while (rs.next()) {
                foodDTO = makeFood(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return foodDTO;
    }

    public int updateFood(FoodDTO foodDTO) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_update);
            st.setString(1, foodDTO.getName());
            st.setInt(2, foodDTO.getPrice());
            st.setInt(3, foodDTO.getMaxCookingTime());
            st.setInt(4, foodDTO.getFoodId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public int deleteFood(int foodId) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_delete);
            st.setInt(1, foodId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }
}