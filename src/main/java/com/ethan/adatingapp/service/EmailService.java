package com.ethan.adatingapp.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//Handle everything related to sending emails
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendResetPasswordEmail(String email, String resetToken) {
        String subject = "Password Reset Request";
        String path = "http://localhost:8080/reset-password?token=" + resetToken;
        String message = "Click the button below to reset your password";
        sendEmail(email , resetToken , subject, path , message);

    }

    public void sendEmail(String email, String token, String subject, String path, String message) {
        String actionUrl = path;

        // Fixed email content with proper quote handling
        String emailContent = "<p>" + message + "</p>" +
                "<a href=\"" + actionUrl + "\" " +
                "style=\"display: inline-block; padding: 10px 20px; font-size: 16px; " +
                "color: #ffffff; background-color: #007bff; text-decoration: none; " +
                "border-radius: 5px;\">Reset Password</a>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(emailContent, true); // true indicates HTML content
            mailSender.send(mimeMessage);
            System.out.println("Email sent successfully to: " + email); // Add success logging
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace(); // Add full stack trace for better debugging
        }
    }
}
