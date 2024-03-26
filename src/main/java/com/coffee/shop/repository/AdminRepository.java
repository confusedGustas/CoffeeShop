package com.coffee.shop.repository;

import com.coffee.shop.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
    Optional<Admin> findByRole(String role);
}
