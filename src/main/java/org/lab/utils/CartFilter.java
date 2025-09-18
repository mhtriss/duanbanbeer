package org.lab.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lab.dao.CartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@WebFilter(urlPatterns = "/*")
public class CartFilter extends HttpFilter {

    @Autowired
    CartDAO cart;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setAttribute("cartTongTien",cart.getTongTien());
        req.setAttribute("cartSoLuong",cart.getSoluong());
        chain.doFilter(req,resp);
    }

}
