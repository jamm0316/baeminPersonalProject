package com.delivery.controller.oderItem;

import entity.OrderItemDTO;
import orderitem.OrderItemRepository;
import orderitem.OrderItemService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/addItem.do")
public class TempInsertItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer foodId = Integer.parseInt(request.getParameter("foodId"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        String customerEmail = (String) session.getAttribute("email");
        List<OrderItemDTO> cart = (ArrayList<OrderItemDTO>) session.getAttribute("cart");
        System.out.println("cart = " + cart);

        Date currentDate = new Date(System.currentTimeMillis());

        OrderItemDTO orderItemDTO = OrderItemDTO.builder()
                .quantity(quantity)
                .foodId(foodId)
                .email(customerEmail)
                .orderDate(currentDate)
                .status("주문확인 중")
                .build();

        cart.add(orderItemDTO);
        System.out.println("after cart = " + cart);
        System.out.println("저장완료");
    }
}
