package com.exam.examPortal.Service.Imp;

import com.exam.examPortal.Service.UserService;
import com.exam.examPortal.model.User;
import com.exam.examPortal.model.UserRole;
import com.exam.examPortal.repo.LoginRespository;
import com.exam.examPortal.repo.RoleRepository;
import com.exam.examPortal.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.server.ResponseStatusException;


import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Checking User is Valid or not and then Add User
    public User createUser(User user, Set<UserRole> userRoles) {
       User local = userRepository.findByEmail(user.getEmail());

        try {
              if (local!= null && local.isEnabled()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with this email already exists ");
            }//user create
            user.setPassword(passwordEncoder.encode(user.getPassword()));
              for (UserRole ur : userRoles)
                {
                    roleRepository.save(ur.getRole());
                }
                user.getUserRoles().addAll(userRoles);
                user.setEnabled(true);
              return userRepository.save(user);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User creation failed");
        }
    }

}
