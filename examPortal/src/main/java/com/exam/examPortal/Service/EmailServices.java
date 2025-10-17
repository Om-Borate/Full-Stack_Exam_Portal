package com.exam.examPortal.Service;

import com.exam.examPortal.model.User;
import com.exam.examPortal.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private UserRepository userRepository;


    public void sendSimpleEmail(String toEmail, String subject,String body) {
        try {

            User local = userRepository.findByEmail(toEmail);
            if(local==null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail); // Ideally configure via application.properties
                message.setTo(toEmail);
                message.setSubject(subject);
                message.setText(body);

                javaMailSender.send(message);
                System.out.println("Email sent successfully to " + toEmail);
            }
            else{
                throw new Exception("Email Already Present in DB");
            }
        } catch (Exception e) {
            System.err.println("Failed to send email to " + toEmail);
            e.printStackTrace();
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }
}
