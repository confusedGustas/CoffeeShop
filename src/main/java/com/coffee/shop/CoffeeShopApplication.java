package com.coffee.shop;

import com.coffee.shop.models.Admin;
import com.coffee.shop.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class CoffeeShopApplication implements CommandLineRunner {
    private final AdminRepository adminRepository;

    public static void main(String[] args) {
        SpringApplication.run(CoffeeShopApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Optional<Admin> adminAccount = adminRepository.findByRole("ADMIN");

        if (adminAccount.isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setRole("ADMIN");

            adminRepository.save(admin);
        }
    }
}
