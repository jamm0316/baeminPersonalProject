package orderitem;

import DBUtil.DBUtil;
import entity.OrderItemDTO;
import entity.OrdersDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // food_id 컬럼을 제외하고 삽입하는 SQL 문
    String sql_insert = "INSERT INTO ORDER_ITEM (QUANTITY, FOOD_ID, EMAIL, ORDER_DATE, STATUS)  VALUES (?, ?, ?, ?, ?)";
    String sql_selectById = "select * from food where EMAIL = ?";
    String sql_selectOrders = """
            SELECT o.order_item_id AS order_item_id,
                        s.name AS store_name,
                        f.name AS food_name,
                        f.price AS price,
                        o.quantity AS quantity,
                        f.max_cooking_time AS max_cooking_time,
                        o.order_date AS order_date,
                        o.status AS status
                 FROM store s
                     JOIN food f ON s.store_id = f.store_id
                     JOIN order_item o ON f.food_id = o.food_id
                 WHERE o.email = ?
            """;
    String sql_selectOrderSummary = """
                SELECT o.*
                FROM ORDER_ITEM o
                JOIN FOOD f ON o.FOOD_ID = f.FOOD_ID
                JOIN STORE s ON f.STORE_ID = s.STORE_ID
                WHERE s.STORE_ID = ? AND o.STATUS != '배달완료'
            """;
    String sql_deleteOrderItem = "delete from ORDER_ITEM where ORDER_ITEM_ID = ?";

    String sql_updateDate = """
            UPDATE ORDER_ITEM SET
                ORDER_DATE = ?,
                STATUS = ?
            WHERE ORDER_ITEM_ID =?
                """;
    String sql_updateStatus = """
            UPDATE ORDER_ITEM SET
                STATUS = ?
            WHERE ORDER_ITEM_ID =?
                """;

    public int createOrderItem(OrderItemDTO orderItemDTO, Date currentDate) {
        int result = 0;
        conn = DBUtil.getConnection();
        try {
            st = conn.prepareStatement(sql_insert);
            st.setInt(1, orderItemDTO.getQuantity());
            st.setInt(2, orderItemDTO.getFoodId());
            st.setString(3, orderItemDTO.getEmail());
            st.setDate(4, currentDate);
            st.setString(5, orderItemDTO.getStatus());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public List<OrdersDTO> selectOrders(String email) {
        OrdersDTO ordersDTO = new OrdersDTO();
        conn = DBUtil.getConnection();
        List<OrdersDTO> ordersList = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql_selectOrders);
            st.setString(1, email);
            rs = st.executeQuery();
            while (rs.next()) {
                ordersDTO = makeOrders(rs);
                ordersList.add(ordersDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return ordersList;
    }

    private OrdersDTO makeOrders(ResultSet rs) throws SQLException {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(rs.getInt("order_item_id"));
        ordersDTO.setStoreName(rs.getString("store_name"));
        ordersDTO.setFoodName(rs.getString("food_name"));
        ordersDTO.setPrice(rs.getInt("price"));
        ordersDTO.setQuntity(rs.getInt("quantity"));
        ordersDTO.setDeliveryTime(rs.getInt("max_cooking_time"));
        ordersDTO.setOrderDate(rs.getDate("order_date"));
        ordersDTO.setStatus(rs.getString("status"));

        return ordersDTO;
    }

    //todo: orderId를 가지고 status, date 수정하기
    public int updateDate(OrderItemDTO orderItemDTO) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_updateDate);
            st.setDate(1, orderItemDTO.getOrderDate());
            st.setString(2, orderItemDTO.getStatus());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public List<OrderItemDTO> selectOrderSummary(int storeId) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        conn = DBUtil.getConnection();
        List<OrderItemDTO> orderItemList = new ArrayList<>();

        try {
            st = conn.prepareStatement(sql_selectOrderSummary);
            st.setInt(1, storeId);
            rs = st.executeQuery();
            while (rs.next()) {
                orderItemDTO = makeOrderItem(rs);
                orderItemList.add(orderItemDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return orderItemList;
    }

    private OrderItemDTO makeOrderItem(ResultSet rs)  throws SQLException{
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(rs.getInt("ORDER_ITEM_ID"));
        orderItemDTO.setQuantity(rs.getInt("QUANTITY"));
        orderItemDTO.setFoodId(rs.getInt("FOOD_ID"));
        orderItemDTO.setEmail(rs.getString("EMAIL"));
        orderItemDTO.setOrderDate(rs.getDate("ORDER_DATE"));
        orderItemDTO.setStatus(rs.getString("STATUS"));

        return orderItemDTO;
    }

    public int deleteOrderItem(int orderId) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_deleteOrderItem);
            st.setInt(1, orderId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }

    public int updateStatus(int orderId, String status) {
        int result = 0;
        conn = DBUtil.getConnection();

        try {
            st = conn.prepareStatement(sql_updateStatus);
            st.setString(1, status);
            st.setInt(2, orderId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, st, rs);
        }
        return result;
    }
}