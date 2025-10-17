package com.exam.examPortal.Controller;

import com.exam.examPortal.Service.EmailServices;
import com.exam.examPortal.Service.OtpService;
import com.exam.examPortal.model.ApiResponse;
import com.exam.examPortal.model.OtpVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {
    @Autowired
    private EmailServices emailServices;
    @Autowired
    private OtpService otpService;

    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpVerification request) {
        String email = request.getEmail();

         System.out.println("send otp controller is called  " + email);

        String otp = otpService.generatedOtp(email);
        String body = "Your OTP for Verification is: " + otp + "\nPlease use this to verify your email.";
        emailServices.sendSimpleEmail(email, "Registration OTP", body);
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid email format"));
        }
        return ResponseEntity.ok(new ApiResponse("Otp sent to mail"));
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerification verify) {
        String email = verify.getEmail();
        String otp = verify.getOtp();
        System.out.println("Email is sent to  "+ email+" and the OTP is :"+otp);

        boolean isvalid=otpService.verifyOtp(email,otp);
        if(!isvalid){
            return ResponseEntity.ok(false);
        }
else{
            return ResponseEntity.ok(true);
        }
    }
}
