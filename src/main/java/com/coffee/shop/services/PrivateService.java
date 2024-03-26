package com.coffee.shop.services;

import com.coffee.shop.dto.*;
import com.coffee.shop.models.About;
import com.coffee.shop.models.Admin;
import com.coffee.shop.models.Contact;
import com.coffee.shop.models.Product;
import com.coffee.shop.repository.AboutRepository;
import com.coffee.shop.repository.AdminRepository;
import com.coffee.shop.repository.ContactRepository;
import com.coffee.shop.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PrivateService {
    private final ProductRepository productRepository;
    private final ContactRepository contactRepository;
    private final AboutRepository aboutRepository;
    private final AdminRepository adminRepository;

    public ResponseEntity<?> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        try {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, response, authentication);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> addItem(AddItemRequest addItemRequest) {
        try {
            Product product = new Product();
            product.setName(addItemRequest.name());
            product.setDescription(addItemRequest.description());
            product.setPrice(addItemRequest.price());
            product.setCategory(addItemRequest.category());
            productRepository.save(product);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> deleteItem(Long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> updateItem(EditItemRequest editItemRequest) {
        try {
            Product product = productRepository.findById(editItemRequest.id()).orElse(null);
            if (product == null) {
                return ResponseEntity.badRequest().build();
            }
            product.setName(editItemRequest.name());
            product.setDescription(editItemRequest.description());
            product.setPrice(editItemRequest.price());
            productRepository.save(product);
            return ResponseEntity.ok().build();
        } catch (Exception error) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<?> updateContact(EditContactRequest editContactRequest) {
        Contact contact = contactRepository.findById(1).orElse(null);
        if (contact == null) {
            Contact newContact = new Contact();
            newContact.setAddress(editContactRequest.address());
            newContact.setEmail(editContactRequest.email());
            newContact.setPhone(editContactRequest.phone());
            newContact.setGithub(editContactRequest.github());
            contactRepository.save(newContact);

            return ResponseEntity.ok().build();
        }

        contact.setAddress(editContactRequest.address());
        contact.setEmail(editContactRequest.email());
        contact.setPhone(editContactRequest.phone());
        contact.setGithub(editContactRequest.github());
        contactRepository.save(contact);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateAbout(EditAboutRequest editAboutRequest) {
        About about = aboutRepository.findById(1).orElse(null);
        if (about == null) {
            About newAbout = new About();
            newAbout.setMessage(editAboutRequest.message());
            aboutRepository.save(newAbout);

            return ResponseEntity.ok().build();
        }

        about.setMessage(editAboutRequest.message());
        aboutRepository.save(about);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updatePassword(EditPasswordRequest editPasswordRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin = adminRepository.findById(1).orElse(null);
        if (admin == null) {
            return ResponseEntity.badRequest().build();
        }

        if (encoder.matches(editPasswordRequest.oldPassword(), admin.getPassword())) {
            admin.setPassword(new BCryptPasswordEncoder().encode(editPasswordRequest.newPassword()));
            adminRepository.save(admin);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
