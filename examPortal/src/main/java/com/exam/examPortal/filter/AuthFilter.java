package com.exam.examPortal.filter;

import com.exam.examPortal.Service.Imp.UserDetailsServiceImpl;
import com.exam.examPortal.Util.JwtUtil;
import com.exam.examPortal.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        System.out.println(authHeader);
        String token = null;
        String username=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
           token= authHeader.substring(7);
           username= jwtUtil.extractUsername(token);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //fetch user from userDetailsService
            UserDetails user =userDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(token,user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // were are setting the all releated request that are coming from http in this
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                System.out.println("Authenticated user: " + auth.getName());
            } else {
                System.out.println("No authenticated user in context.");
            }

            System.out.println("Injected userDetails: " + user);
        }
        filterChain.doFilter(request,response);
    }
}
