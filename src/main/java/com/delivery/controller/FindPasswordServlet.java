package com.delivery.controller;

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

@WebServlet("/check/findPass.do")
public class FindPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String authority = req.getParameter("authority");
        if (authority.equals("customer")) {
            req.getRequestDispatcher("/customer/findPassword.jsp").forward(req, resp);
        } else if (authority.equals("owner")) {
            req.getRequestDispatcher("/owner/findPassword.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authority = request.getParameter("authority");
        if (authority.equals("customer")) {
            CustomerRepository customerRepository = new CustomerRepository();
            CustomerService customerService = new CustomerService(customerRepository);

            String userId = request.getParameter("userId");
            String userName = request.getParameter("userName");

            CustomerDTO customerDTO = customerService.selectByEmail(userId);
            String message = "";
            //1. 아이디가 없을 때
            if (customerDTO.getEmail() == null) {
                HttpSession session = request.getSession();
                session.setAttribute("found", false);
                session.setAttribute("notFound", true);
                message = "비밀번호를 찾지 못했어요!";
                request.setAttribute("userId", userId);
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/customer/resultPassword.jsp").forward(request, response);
            }
            //2. 아이디가 있을 때
            else if (customerDTO.getName().equals(userName)) {
                HttpSession session = request.getSession();
                session.setAttribute("found", true);
                session.setAttribute("notFound", false);
                String password = customerDTO.getPassword();
                message = "비밀번호를 찾았어요!!";
                request.setAttribute("userId", userId);
                request.setAttribute("userPassword", password);
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/customer/resultPassword.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("found", false);
                session.setAttribute("notFound", true);
                message = "이름을 잘못입력하신 것 같아요!";
                request.setAttribute("userId", userId);
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/customer/resultPassword.jsp").forward(request, response);
            }

        } else if (authority.equals("owner")) {
            OwnerRepository ownerRepository = new OwnerRepository();
            OwnerService ownerService = new OwnerService(ownerRepository);

            String userId = request.getParameter("userId");
            String userName = request.getParameter("userName");

            OwnerDTO ownerDTO = ownerService.selectByEmail(userId);
            String message = "";
            //1. 아이디가 없을 때
            if (ownerDTO.getEmail() == null) {
                HttpSession session = request.getSession();
                session.setAttribute("found", false);
                session.setAttribute("notFound", true);
                message = "비밀번호를 찾지 못했어요!";
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/owner/resultPassword.jsp").forward(request, response);
            }
            //2. 아이디가 있을 때
            else if (ownerDTO.getName().equals(userName)) {
                HttpSession session = request.getSession();
                session.setAttribute("found", true);
                session.setAttribute("notFound", false);
                String password = ownerDTO.getPassword();
                message = "비밀번호를 찾았어요!!";
                request.setAttribute("userId", userId);
                request.setAttribute("userPassword", password);
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/owner/resultPassword.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("found", false);
                session.setAttribute("notFound", true);
                message = "이름을 잘못입력하신 것 같아요!";
                request.setAttribute("alertMessage", message);

                request.getRequestDispatcher("/owner/resultPassword.jsp").forward(request, response);
            }
        }
    }
}
