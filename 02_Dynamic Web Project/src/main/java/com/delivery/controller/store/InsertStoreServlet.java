package com.delivery.controller.store;

import entity.OwnerDTO;
import entity.StoreDTO;
import store.StoreRepository;
import store.StoreService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/insertStore.do")
public class InsertStoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storeName = request.getParameter("store-name");
        Integer storeArea = Integer.parseInt(request.getParameter("store-area"));

        Integer storeCategory = Integer.parseInt(request.getParameter("store-category"));
        HttpSession session = request.getSession();
        OwnerDTO ownerDTO = (OwnerDTO) session.getAttribute("ownerDTO");
        String email = ownerDTO.getEmail();

        StoreDTO storeDTO = StoreDTO.builder()
                .name(storeName)
                .area_id(storeArea)
                .ownerEmail(email)
                .category(storeCategory)
                .build();

        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);
        storeService.join(storeDTO);

        String contextPath = getServletContext().getContextPath();
        request.setAttribute("storeName", storeName);
        response.sendRedirect(contextPath + "/ownerDashboard/storeManagement.do");
    }
}
