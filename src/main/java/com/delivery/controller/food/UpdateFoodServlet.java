package com.delivery.controller.food;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("")
public class UpdateFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer foodId = Integer.parseInt(request.getParameter("foodId"));
        String foodName = request.getParameter("foodName");
        Integer foodPrice = Integer.parseInt(request.getParameter("foodPrice"));
        Integer maxCookingTime = Integer.parseInt(request.getParameter("foodMaxCookingTime"));

    }
}
