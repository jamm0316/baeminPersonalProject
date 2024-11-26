package com.delivery.controller;

import com.google.gson.Gson;
import customer.CustomerRepository;
import customer.CustomerService;
import entity.CustomerDTO;
import entity.OwnerDTO;
import owner.OwnerRepository;
import owner.OwnerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CheckDuplicate.do")
public class EmailCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authority = request.getParameter("authority");
        if (authority.equals("customer")) {
            String email = request.getParameter("email");
            CustomerRepository customerRepository = new CustomerRepository();
            CustomerService customerService = new CustomerService(customerRepository);

            CustomerDTO customerDTO = customerService.selectByEmail(email);

            boolean isDuplicate = email != null && email.equalsIgnoreCase(customerDTO.getEmail());

            Map<String, Boolean> result = new HashMap<>();
            result.put("isDuplicate", isDuplicate);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            String jsonResponse = new Gson().toJson(result);
            response.getWriter().write(jsonResponse);
        } else if (authority.equals("owner")) {
            String email = request.getParameter("email");

            OwnerRepository ownerRepository = new OwnerRepository();
            OwnerService ownerService = new OwnerService(ownerRepository);

            OwnerDTO ownerDTO = ownerService.selectByEmail(email);

            boolean isDuplicate = email != null && email.equalsIgnoreCase(ownerDTO.getEmail());

            Map<String, Boolean> result = new HashMap<>();
            result.put("isDuplicate", isDuplicate);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            String jsonResponse = new Gson().toJson(result);
            response.getWriter().write(jsonResponse);
        }

    }
}
