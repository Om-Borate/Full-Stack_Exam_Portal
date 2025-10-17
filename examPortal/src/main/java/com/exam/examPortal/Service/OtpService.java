package com.exam.examPortal.Service;

import com.exam.examPortal.model.OtpVerification;
import com.exam.examPortal.model.User;
import com.exam.examPortal.repo.OtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
@Service
public class OtpService {
    @Autowired
    private OtpRepository otpRepository;
    @Transactional
    public String generatedOtp(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        LocalDateTime expiry = LocalDateTime.now().plusMinutes(50);

        otpRepository.deleteAllByEmail(email);
        //Delete the email for Otp authentication  to check
        OtpVerification otpVerification  = new OtpVerification();
        otpVerification.setOtp(otp);
        otpVerification.setEmail(email);
        otpVerification.setExpirationTime(expiry);

        otpRepository.save(otpVerification);

        return otp;
    }
    @Transactional
    public boolean verifyOtp(String email, String otp) {
        Optional<OtpVerification> match = otpRepository.findByEmailAndOtp(email, otp);

        if (match.isPresent() && match.get().getExpirationTime().isAfter(LocalDateTime.now())) {

            otpRepository.deleteByEmail(email);

            return true;

        }
        return false;
    }
}
