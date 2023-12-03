package com.usermgmt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestedURI = httpRequest.getRequestURI();
        if (isAllowedPage(requestedURI)) {
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpResponse.setHeader("Pragma", "no-cache");
            httpResponse.setDateHeader("Expires", 0);
            chain.doFilter(request, response);
            return;
        }
        if (session == null || session.getAttribute("username") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            return;

        }
        String userRole = (String) session.getAttribute("userRole");
        if (userRole != null && userRole.equals("admin")) {
            if (requestedURI.endsWith("/deleteUser") || requestedURI.endsWith("/editUser")) {
                chain.doFilter(request, response);
                return;
            }
        }
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        chain.doFilter(request, response);
    }

    private boolean isAllowedPage(String requestedURI) {
        String[] allowedPages = { "/login.jsp","login","/register.jsp","register", "/registration-error.jsp"};
        for (String page : allowedPages) {
            if (requestedURI.endsWith(page)) {
                return true;
            }
        }
        return false;
    }
    public void destroy() {
    }
}
