package orderitem;

import entity.OrderItemDTO;
import entity.OrdersDTO;

import java.sql.Date;
import java.util.List;

public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository memberRepository) {
        this.orderItemRepository = memberRepository;
    }

    public int createOrderItem(OrderItemDTO orderItemDTO, Date currentDate) {
        return orderItemRepository.createOrderItem(orderItemDTO, currentDate);
    }

    public List<OrdersDTO> selectOrders(String email) {
        return orderItemRepository.selectOrders(email);
    }

    public int updateDate(OrderItemDTO orderItemDTO) {
        return orderItemRepository.updateDate(orderItemDTO);
    }

    public List<OrderItemDTO> selectOrderSummary(int storeId) {
        return orderItemRepository.selectOrderSummary(storeId);
    }

    public int deleteOrderItem(int orderId) {
        return orderItemRepository.deleteOrderItem(orderId);
    }

    public int updateStatus(int orderId, String status) {
        return orderItemRepository.updateStatus(orderId, status);
    }
}