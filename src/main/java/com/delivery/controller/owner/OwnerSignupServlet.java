package com.delivery.controller.owner;

import entity.OwnerDTO;
import owner.OwnerRepository;
import owner.OwnerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/owner/signup.do")
public class OwnerSignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/owner/signup.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        OwnerRepository ownerRepository = new OwnerRepository();
        OwnerService ownerService = new OwnerService(ownerRepository);
        if (userId != null) {
            String userPassword = request.getParameter("userPassword");
            String keepLoginStatus = request.getParameter("keep-login-status");

            OwnerDTO ownerDTO = ownerService.login(userId, userPassword);
            HttpSession session = request.getSession();
            session.setAttribute("ownerDTO", ownerDTO);
            String alertMessage = "";
            if (ownerDTO.getEmail().equals("hasNotEmailUser")) {
                session.setAttribute("isLoginFailed", true);
                session.setAttribute("userId", userId);
                session.setAttribute("keepLoginStatus", keepLoginStatus != null);
                alertMessage = "아이디를 다시 한번 확인해 주세요.";
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("signup.do");
                return;
            } else if (ownerDTO.getPassword().equals("WrongPasswordUser")) {
                session.setAttribute("isLoginFailed", true);
                session.setAttribute("userId", userId);
                session.setAttribute("keepLoginStatus", keepLoginStatus != null);
                alertMessage = "비밀번호를 다시 한번 확인해 주세요.";
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("signup.do");

                return;
            }

//        HttpSession session = request.getSession();
//        session.setAttribute("loginMember", customerDTO.getName());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ownerDashboard/mainBoard.do");
            dispatcher.forward(request, response);
        }

        //2. 회원가입
        else {
            String signUpEmail = request.getParameter("signUpEmail");
            String signUpName = request.getParameter("signUpName");
            String signUpPassword = request.getParameter("signUpPassword");

            OwnerDTO ownerDTO = OwnerDTO.builder()
                    .email(signUpEmail)
                    .name(signUpName)
                    .password(signUpPassword)
                    .build();

            ownerService.join(ownerDTO);

            HttpSession session = request.getSession();

            session.setAttribute("email", ownerDTO.getEmail());
            session.setAttribute("name", ownerDTO.getName());
            session.setAttribute("password", ownerDTO.getPassword());

            request.getRequestDispatcher("/ownerDashboard/mainBoard.do").forward(request, response);
        }
    }
}
