package com.exam.examPortal.Service;

import com.exam.examPortal.Util.JwtUtil;
import com.exam.examPortal.model.User;
import com.exam.examPortal.repo.LoginRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthLoginService {
    @Autowired
    private LoginRespository loginRespository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;
    public String loginUser(User loginRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            String token = jwtUtil.loginUser(loginRequest.getEmail());
            System.out.println("Generated JWT Token: " + token); // ✅ Debug print

            return token;

        }catch (Exception e) {
                e.printStackTrace(); // or use logger
                throw new RuntimeException("Login failed: " + e.getMessage());
            }

        }
    }
