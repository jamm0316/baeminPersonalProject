package com.delivery.filter;
import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.do")
public class EncodingFilter extends HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
}
