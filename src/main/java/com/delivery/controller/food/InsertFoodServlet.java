package com.delivery.controller.food;

import entity.FoodDTO;
import food.FoodRepository;
import food.FoodService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/insertFood.do")
public class InsertFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 읽기
            String name = request.getParameter("foodName");
            String priceParam = request.getParameter("foodPrice");
            String cookingTimeParam = request.getParameter("foodMaxCookingTime");
            String storeIdParam = request.getParameter("storeId");

            // 파라미터 유효성 검사
            if (name == null || name.isEmpty() || priceParam == null || cookingTimeParam == null || storeIdParam == null) {
                throw new IllegalArgumentException("요청 파라미터가 유효하지 않습니다.");
            }

            // 파라미터 변환
            Integer price = Integer.parseInt(priceParam);
            Integer cookingTime = Integer.parseInt(cookingTimeParam);
            Integer storeId = Integer.parseInt(storeIdParam);

            // 음식 데이터 생성
            FoodRepository foodRepository = new FoodRepository();
            FoodService foodService = new FoodService(foodRepository);

            FoodDTO foodDTO = FoodDTO.builder()
                    .name(name)
                    .price(price)
                    .maxCookingTime(cookingTime)
                    .storeId(storeId)
                    .build();

            foodService.join(foodDTO);

            // 음식 목록 조회 및 반환
            List<FoodDTO> foodDTOS = foodService.selectByStoreId(storeId);
            request.setAttribute("foodList", foodDTOS);
            request.getRequestDispatcher("/ownerDashboard/foodListFragment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "요청 처리 중 오류 발생");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
