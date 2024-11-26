package contorller;

import entity.OrderItemDTO;
import entity.OrdersDTO;
import orderitem.OrderItemService;

import java.sql.Date;
import java.util.List;

public class OrderItemController {
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    public int createOrderItem(OrderItemDTO orderItemDTO, Date currentDate) {
        return orderItemService.createOrderItem(orderItemDTO, currentDate);
    }

    public List<OrdersDTO> selectOrders(String email) {
        return orderItemService.selectOrders(email);
    }

    public int updateDate(OrderItemDTO orderItemDTO) {
        return orderItemService.updateDate(orderItemDTO);
    }

    public List<OrderItemDTO> selectOrderSummary(int storeId) {
        return orderItemService.selectOrderSummary(storeId);
    }

    public int deleteOrderItem(int orderId) {
        return orderItemService.deleteOrderItem(orderId);
    }

    public int updateStatus(int orderId, String status) {
        return orderItemService.updateStatus(orderId, status);
    }

//    public void deleteMember(String email) {
//        foodService.deleteMember(email);
//    }
}
