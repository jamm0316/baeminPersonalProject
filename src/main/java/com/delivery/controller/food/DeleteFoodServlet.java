package com.delivery.controller.food;

import DBUtil.ParseUtil;
import entity.FoodDTO;
import food.FoodRepository;
import food.FoodService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteFood.do")
public class DeleteFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer foodId = Integer.parseInt(request.getParameter("foodId"));
            Integer storeId = Integer.parseInt(request.getParameter("storeId"));

            FoodRepository foodRepository = new FoodRepository();
            FoodService foodService = new FoodService(foodRepository);

            int i = foodService.deleteFood(foodId);

            List<FoodDTO> foodDTOS = foodService.selectByStoreId(storeId);
            request.setAttribute("foodList", foodDTOS);
            request.getRequestDispatcher("/ownerDashboard/foodListFragment.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }
}
