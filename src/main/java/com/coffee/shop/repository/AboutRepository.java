package com.coffee.shop.repository;

import com.coffee.shop.models.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<About, Integer> {

}
