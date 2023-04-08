package com.niit.Pizza.Order.Details.filter;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        String authHeader=httpServletRequest.getHeader("Authorization");
        System.out.println("Auth Header "+authHeader);
        ServletOutputStream servletOutputStream= servletResponse.getOutputStream();
        if (authHeader==null || !authHeader.startsWith("Bearer"))
        {
            //invalid user
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("Invalid token or missing token");
            servletOutputStream.close();
        }
        else
        {
            // valid User
            String jwtToken=authHeader.substring(7);
            System.out.println("Jwt Token "+jwtToken);
            String username= Jwts.parser().setSigningKey("securityKey").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("First name", username);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

}
