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

@WebServlet("/cart/order/insertOrders.do")
public class InsertItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderItemDTO> cart = (ArrayList<OrderItemDTO>) session.getAttribute("cart");
        System.out.println("cart = " + cart);

        OrderItemRepository orderItemRepository = new OrderItemRepository();
        OrderItemService orderItemService = new OrderItemService(orderItemRepository);

        Date currentDate = new Date(System.currentTimeMillis());

        for (OrderItemDTO orderItemDTO : cart) {
            orderItemService.createOrderItem(orderItemDTO, currentDate);
        }

        session.removeAttribute("cart");
        List<OrderItemDTO> newCart = new ArrayList<>();
        session.setAttribute("cart", newCart);
    
        String contextPath = request.getServletContext().getContextPath();
        response.sendRedirect(contextPath + "/util/categoryMain.do");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
