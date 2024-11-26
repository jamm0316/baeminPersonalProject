package com.delivery.controller.category;

import entity.StoreDTO;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/util/detail/categorySelect.do")
public class SelectCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));

        HttpSession session = request.getSession();
        Integer customerLocation = (Integer) session.getAttribute("location");

        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);

        List<StoreDTO> storeDTOS = storeService.selectByCategory(categoryId);
        List<StoreDTO> storeList = new ArrayList<>();

        for (StoreDTO storeDTO : storeDTOS) {
            if (storeDTO.getCategory() == customerLocation) {
                storeList.add(storeDTO);
            }
        }

        request.setAttribute("storeList", storeList);
        switch (categoryId) {
            case 1 -> {request.getRequestDispatcher("chicken.jsp").forward(request, response);}
            case 2 -> {request.getRequestDispatcher("chinese.jsp").forward(request, response);}
            case 3 -> {request.getRequestDispatcher("donkazhu.jsp").forward(request, response);}
            case 4 -> {request.getRequestDispatcher("pizza.jsp").forward(request, response);}
            case 5 -> {request.getRequestDispatcher("sushi.jsp").forward(request, response);}
            case 6 -> {request.getRequestDispatcher("mela.jsp").forward(request, response);}
            case 7 -> {request.getRequestDispatcher("beef.jsp").forward(request, response);}
            case 8 -> {request.getRequestDispatcher("dessert.jsp").forward(request, response);}
            case 9 -> {request.getRequestDispatcher("sideMeal.jsp").forward(request, response);}
            case 10 -> {request.getRequestDispatcher("coffee.jsp").forward(request, response);}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
