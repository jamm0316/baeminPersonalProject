package com.delivery.controller.store;

import entity.OwnerDTO;
import entity.StoreDTO;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/editStore.do")
public class UpdateStoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("store-name");
        Integer areaId = Integer.parseInt(request.getParameter("store-area"));
        Integer category = Integer.parseInt(request.getParameter("store-category"));
        Integer storeId = Integer.parseInt(request.getParameter("store-id"));

        HttpSession session = request.getSession();
        OwnerDTO ownerDTO = (OwnerDTO) session.getAttribute("ownerDTO");
        String email = ownerDTO.getEmail();

        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);

        StoreDTO storeDTO = StoreDTO.builder()
                .id(storeId)
                .name(name)
                .area_id(areaId)
                .ownerEmail(email)
                .category(category)
                .build();

        storeService.updateStore(storeDTO);

        String contextPath = getServletContext().getContextPath();
        response.sendRedirect(contextPath + "/ownerDashboard/storeManagement.do");
    }
}
