package com.delivery.controller.oderItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.FoodDTO;
import entity.OrderItemDTO;
import entity.OrdersDTO;
import entity.StoreDTO;
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

@WebServlet("/cart/order/orderList.do")
public class SelectOrderItems extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("서블릿 실행");
        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("email");
        List<OrderItemDTO> cart = (ArrayList<OrderItemDTO>) session.getAttribute("cart");
        System.out.println("cart = " + cart);
        System.out.println("customerEmail = " + customerEmail);

        FoodRepository foodRepository = new FoodRepository();
        FoodService foodService = new FoodService(foodRepository);

        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);

        List<OrdersDTO> orderList = new ArrayList<>();
        int totalAmount = 0;

        for (OrderItemDTO orderItemDTO : cart) {
            int foodId = orderItemDTO.getFoodId();
            FoodDTO foodDTO = foodService.selectByFoodid(foodId);
            int price = foodDTO.getPrice();

            int quantity = orderItemDTO.getQuantity();
            totalAmount += (price * quantity);

            int storeId = foodDTO.getStoreId();
            StoreDTO storeDTO = storeService.selectByStoreId(storeId);
            String storeName = storeDTO.getName();

            String foodName = foodDTO.getName();
            int maxCookingTime = foodDTO.getMaxCookingTime();
            Date currentDate = new Date(System.currentTimeMillis());


            OrdersDTO ordersDTO = OrdersDTO.builder()
                    .storeName(storeName)
                    .foodName(foodName)
                    .price(price)
                    .quntity(quantity)
                    .deliveryTime(maxCookingTime)
                    .orderDate(currentDate)
                    .status("주문확인 중")
                    .build();

            orderList.add(ordersDTO);
        }

        request.setAttribute("orderList", orderList);
        request.setAttribute("totalAmount", totalAmount);
        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
