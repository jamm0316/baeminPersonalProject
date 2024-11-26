package com.delivery.controller.food;

import com.google.gson.Gson;
import entity.FoodDTO;
import food.FoodRepository;
import food.FoodService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectFood.do")
public class SelectFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer storeId = 0;

        String getParam = request.getParameter("storeId");
        Integer getAtt = (Integer) request.getAttribute("storeId");

        if (getParam != null) {
            storeId = Integer.parseInt(getParam);
        } else if (getAtt != null) {
            storeId = getAtt;
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "storeId parameter is missing.");
            return;
        }

        FoodRepository foodRepository = new FoodRepository();
        FoodService foodService = new FoodService(foodRepository);
        List<FoodDTO> foodDTOS = foodService.selectByStoreId(storeId);

        request.setAttribute("foodList", foodDTOS);
        request.getRequestDispatcher("/ownerDashboard/foodListFragment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
