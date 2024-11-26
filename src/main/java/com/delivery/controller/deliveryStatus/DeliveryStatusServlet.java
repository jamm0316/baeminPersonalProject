package com.delivery.controller.deliveryStatus;

import entity.OrdersDTO;
import orderitem.OrderItemRepository;
import orderitem.OrderItemService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/delivery/deliveryStatus.do")
public class DeliveryStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        OrderItemRepository orderItemRepository = new OrderItemRepository();
        OrderItemService orderItemService = new OrderItemService(orderItemRepository);

        List<OrdersDTO> orderList = orderItemService.selectOrders(email);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("deliveryStatus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
