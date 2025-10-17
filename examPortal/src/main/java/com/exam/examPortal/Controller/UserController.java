package com.exam.examPortal.Controller;

import com.exam.examPortal.Service.UserService;
import com.exam.examPortal.model.Role;
import com.exam.examPortal.model.User;
import com.exam.examPortal.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/reg")
    public ResponseEntity<User> addUser(@RequestBody User adduser){
        System.out.println("Registration Request");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();

        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();

        userRole.setRole(role);
        userRole.setUser(adduser);

        roles.add(userRole);

        return ResponseEntity.ok(userService.createUser(adduser,roles));
    }
}
