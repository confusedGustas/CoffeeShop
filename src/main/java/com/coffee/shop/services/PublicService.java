package com.coffee.shop.services;

import com.coffee.shop.dto.LoginRequest;
import com.coffee.shop.dto.SendEmailRequest;
import com.coffee.shop.models.About;
import com.coffee.shop.models.Admin;
import com.coffee.shop.models.Contact;
import com.coffee.shop.models.Product;
import com.coffee.shop.repository.AboutRepository;
import com.coffee.shop.repository.AdminRepository;
import com.coffee.shop.repository.ContactRepository;
import com.coffee.shop.repository.ProductRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PublicService {
    private final AdminRepository adminRepository;
    private final ProductRepository productRepository;
    private final ContactRepository contactRepository;
    private final JavaMailSender javaMailService;
    private final AboutRepository aboutRepository;

    public ResponseEntity<?> login(LoginRequest loginRequest, HttpSession session) {
        Admin admin = adminRepository.findByUsername(loginRequest.username());

        if (admin == null || !new BCryptPasswordEncoder().matches(loginRequest.password(), admin.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(admin.getRole());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password(), Collections.singletonList(authority));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> csrf(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            Cookie csrfCookie = new Cookie("XSRF-TOKEN", csrfToken.getToken());
            csrfCookie.setPath("/");
            response.addCookie(csrfCookie);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<?> getProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok().body(products);
    }

    public ResponseEntity<?> getContact() {
        Contact contact = contactRepository.findById(1).orElse(null);

        if (contact == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(contact);
    }

    public ResponseEntity<?> sendEmail(SendEmailRequest sendEmailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(sendEmailRequest.email());
            message.setSubject("Coffee Shop");
            message.setText(sendEmailRequest.firstName() + " " + sendEmailRequest.lastName() + " says: " + sendEmailRequest.message());
            javaMailService.send(message);

            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> getAbout() {
        About about = aboutRepository.findById(1).orElse(null);

        if (about == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(about.getMessage());
    }
}
