package com.exam.examPortal.Service;

import com.exam.examPortal.model.User;
import com.exam.examPortal.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    default User createUser(User user, Set<UserRole> userRole) {
        return null;
    }


}
