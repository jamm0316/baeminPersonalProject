package com.delivery.controller.customer;

import DBUtil.ParseUtil;
import customer.CustomerRepository;
import customer.CustomerService;
import entity.CustomerDTO;
import entity.OrderItemDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customer/signup.do")
public class CustomerSignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/customer/signup.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        //1. 로그인
        String userId = request.getParameter("userId");
        if (userId != null) {
            String userPassword = request.getParameter("userPassword");
            String keepLoginStatus = request.getParameter("keep-login-status");

            CustomerDTO customerDTO = customerService.login(userId, userPassword);

            String alertMessage = "";
            if (customerDTO.getEmail().equals("hasNotEmailUser")) {
                HttpSession session = request.getSession();
                session.setAttribute("isLoginFailed", true);
                session.setAttribute("userId", userId);
                session.setAttribute("keepLoginStatus", keepLoginStatus != null);
                alertMessage = "아이디를 다시 한번 확인해 주세요.";
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("signup.do");
                return;
            } else if (customerDTO.getPassword().equals("WrongPasswordUser")) {
                HttpSession session = request.getSession();
                session.setAttribute("isLoginFailed", true);
                session.setAttribute("userId", userId);
                session.setAttribute("keepLoginStatus", keepLoginStatus != null);
                alertMessage = "비밀번호를 다시 한번 확인해 주세요.";
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("signup.do");
                return;
            }


            HttpSession session = request.getSession();
            //todo: 카트가 있으면 그거 그대로 쓰기, 없으면 생성하기 // 카트가 없으면 생성하라 했는데 안된듯함.
            List<OrderItemDTO> cartList = (List<OrderItemDTO>) session.getAttribute("cart");
            if (cartList == null) {
                cartList = new ArrayList<>();
                session.setAttribute("cart", cartList);
            }

            session.setAttribute("name", customerDTO.getName());
            session.setAttribute("email", customerDTO.getEmail());
            session.setAttribute("nickName", customerDTO.getNickName());
            session.setAttribute("location", customerDTO.getLocation());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/util/category.jsp");
            dispatcher.forward(request, response);
        }

        //2. 회원가입
        //todo: 회원가입 정상적으로 되는 지확인하기
        //todo: 비밀번호 찾기 서블릿 만들기
        else {
            String signUpName = request.getParameter("signUpName");
            String signUpPassword = request.getParameter("signUpPassword");
            String signUpEmail = request.getParameter("signUpEmail");
            String signUpNickname = request.getParameter("signUpNickname");
            int signUpLocation = ParseUtil.areaStringParseInt(request.getParameter("signUpLocation"));

            CustomerDTO customerDTO = CustomerDTO.builder()
                    .name(signUpName)
                    .password(signUpPassword)
                    .email(signUpEmail)
                    .nickName(signUpNickname)
                    .location(signUpLocation)
                    .build();

            customerService.join(customerDTO);

            HttpSession session = request.getSession();
            session.setAttribute("name", customerDTO.getName());
            session.setAttribute("email", customerDTO.getEmail());
            session.setAttribute("nickName", customerDTO.getNickName());
            session.setAttribute("location", customerDTO.getLocation());

            request.getRequestDispatcher("/util/category.jsp").forward(request, response);
        }
    }
}
