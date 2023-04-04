package org.top.diplom_project_shop.config;

import jakarta.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.top.diplom_project_shop.model.dao.client.DbDaoClientImpl;

import java.io.IOException;


@Component
@Order(5)
public class CommonAttributeFilterConfig implements Filter {
    @Autowired
    private DbDaoClientImpl daoClient;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            String role = daoClient.getClientByLogin(auth.getName()).getRole();
            servletRequest.setAttribute("isAuth", role);
        } else {
            servletRequest.setAttribute("isAuth", "anon");

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
