package com.delivery.controller.oderItem;

import orderitem.OrderItemRepository;
import orderitem.OrderItemService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/ownerDashboard/updateOrder.do")
public class UpdateOrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        OrderItemService orderItemService = new OrderItemService(new OrderItemRepository());
        orderItemService.updateStatus(orderId, status);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
