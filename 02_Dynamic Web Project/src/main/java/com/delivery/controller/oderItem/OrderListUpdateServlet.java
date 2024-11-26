package com.delivery.controller.oderItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import entity.*;
import food.FoodRepository;
import food.FoodService;
import orderitem.OrderItemRepository;
import orderitem.OrderItemService;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orderListUpdate.do")
public class OrderListUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDTO ownerDTO = (OwnerDTO) session.getAttribute("ownerDTO");
        String ownerEmail = ownerDTO.getEmail();

        StoreService storeService = new StoreService(new StoreRepository());
        List<StoreDTO> storeDTOS = storeService.selectByEmail(ownerEmail);

        List<OrdersDTO> orderList = new ArrayList<>();

        for (StoreDTO storeDTO : storeDTOS) {
            OrderItemService orderService = new OrderItemService(new OrderItemRepository());
            List<OrderItemDTO> orderItemDTOS = orderService.selectOrderSummary(storeDTO.getId());
            for (OrderItemDTO orderItemDTO : orderItemDTOS) {
                int foodId = orderItemDTO.getFoodId();
                FoodService foodService = new FoodService(new FoodRepository());
                FoodDTO foodDTO = foodService.selectByFoodid(foodId);

                int id = orderItemDTO.getId();
                String storeName = storeDTO.getName();
                String foodName = foodDTO.getName();
                int price = foodDTO.getPrice();
                int quantity = orderItemDTO.getQuantity();
                int maxCookingTime = foodDTO.getMaxCookingTime();
                Date orderDate = orderItemDTO.getOrderDate();
                String status = orderItemDTO.getStatus();

                OrdersDTO eachOrder = OrdersDTO.builder()
                        .id(id)
                        .storeName(storeName)
                        .foodName(foodName)
                        .price(price)
                        .quntity(quantity)
                        .deliveryTime(maxCookingTime)
                        .orderDate(orderDate)
                        .status(status)
                        .build();

                orderList.add(eachOrder);
            }
        }

        // JSON 변환 및 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(orderList);
        response.getWriter().
        write(jsonResponse);

    }
}