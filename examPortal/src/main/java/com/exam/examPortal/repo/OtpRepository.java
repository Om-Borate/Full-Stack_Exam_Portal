package com.exam.examPortal.repo;

import com.exam.examPortal.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpVerification,String> {

    void deleteAllByEmail(String email);

    Optional<OtpVerification> findByEmailAndOtp(String email, String otp);

    void deleteByEmail(String email);
}
