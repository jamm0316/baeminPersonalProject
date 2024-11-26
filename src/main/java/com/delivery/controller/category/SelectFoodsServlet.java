package com.delivery.controller.category;

import com.google.gson.Gson;
import entity.FoodDTO;
import food.FoodRepository;
import food.FoodService;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/util/detail/foodSelect.do")
public class SelectFoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer storeId = Integer.parseInt(request.getParameter("storeId"));

        FoodRepository foodRepository = new FoodRepository();
        FoodService foodService = new FoodService(foodRepository);

        List<FoodDTO> foodList = foodService.selectByStoreId(storeId);

        System.out.println("foodList = " + foodList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new Gson().toJson(foodList));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
