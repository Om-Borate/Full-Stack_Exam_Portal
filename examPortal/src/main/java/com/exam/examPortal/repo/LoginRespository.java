package com.exam.examPortal.repo;

import com.exam.examPortal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRespository extends JpaRepository<User,String> {
    User findByEmail(String email);
}
