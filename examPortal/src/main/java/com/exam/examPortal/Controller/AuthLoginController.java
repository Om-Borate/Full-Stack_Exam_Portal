package com.exam.examPortal.Controller;

import com.exam.examPortal.Service.AuthLoginService;
import com.exam.examPortal.Service.Imp.UserDetailsServiceImpl;
import com.exam.examPortal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthLoginController {
    @Autowired
    private AuthLoginService authLoginService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User loginRequest) {
        System.out.println("Login Request");
        System.out.println("User"+loginRequest.getEmail()+" "+ loginRequest.getPassword());
        String token = authLoginService.loginUser(loginRequest);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/current-user")
    public ResponseEntity<?> secureEndpoint(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Current-User");
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
        User user = (User) userDetails;
        return ResponseEntity.ok(user);
    }


}
