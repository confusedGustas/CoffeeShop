package com.coffee.shop.controllers;

import com.coffee.shop.dto.*;
import com.coffee.shop.services.PrivateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/private")
@RestController
@AllArgsConstructor
public class PrivateController {
    private final PrivateService privateService;

    @GetMapping("/test1")
    public ResponseEntity<?> test1() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        return privateService.logout(authentication, request, response);
    }

    @PostMapping("/edit/add-item")
    public ResponseEntity<?> addItem(@RequestBody @Valid AddItemRequest addItemRequest) {
        return privateService.addItem(addItemRequest);
    }

    @DeleteMapping("/edit/delete-item")
    public ResponseEntity<?> deleteItem(@RequestParam Long id) {
        return privateService.deleteItem(id);
    }

    @PutMapping("/edit/update-item")
    public ResponseEntity<?> updateItem(@RequestBody @Valid EditItemRequest editItemRequest) {
        return privateService.updateItem(editItemRequest);
    }

    @PutMapping("/edit/contact")
    public ResponseEntity<?> updateContact(@RequestBody @Valid EditContactRequest editContactRequest) {
        return privateService.updateContact(editContactRequest);
    }

    @PutMapping("/edit/about")
    public ResponseEntity<?> updateAbout(@RequestBody EditAboutRequest editAboutRequest) {
        return privateService.updateAbout(editAboutRequest);
    }

    @PutMapping("/edit/password")
    public ResponseEntity<?> updatePassword(@RequestBody EditPasswordRequest editPasswordRequest) {
        return privateService.updatePassword(editPasswordRequest);
    }
}
