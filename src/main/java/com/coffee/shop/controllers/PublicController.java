package com.coffee.shop.controllers;

import com.coffee.shop.dto.LoginRequest;
import com.coffee.shop.dto.SendEmailRequest;
import com.coffee.shop.services.PublicService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/public")
@RestController
@AllArgsConstructor
public class PublicController {
    private final PublicService publicService;

    @GetMapping("/test1")
    public ResponseEntity<?> test1() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2() {
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        return publicService.login(loginRequest, session);
    }

    @GetMapping("/csrf")
    public ResponseEntity<?> csrf(HttpServletRequest request, HttpServletResponse response) {
        return publicService.csrf(request, response);
    }

    @GetMapping("/get-products")
    public ResponseEntity<?> getProducts() {
        return publicService.getProducts();
    }

    @GetMapping("/get-contact")
    public ResponseEntity<?> getContact() {
        return publicService.getContact();
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody SendEmailRequest sendEmailRequest) {
        return publicService.sendEmail(sendEmailRequest);
    }

    @GetMapping("/get-about")
    public ResponseEntity<?> getAbout() {
        return publicService.getAbout();
    }
}
