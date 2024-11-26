package com.delivery.controller.food;

import entity.OwnerDTO;
import entity.StoreDTO;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/ownerDashboard/foodManagement.do")
public class FoodManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDTO ownerDTO = (OwnerDTO) session.getAttribute("ownerDTO");
        String email = ownerDTO.getEmail();

        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);

        List<StoreDTO> storeDTOS = storeService.selectByEmail(email);

        session.setAttribute("storeDTOS", storeDTOS);
        request.getRequestDispatcher("foodManagement.jsp").forward(request, response);
    }
}
