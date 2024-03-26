package com.coffee.shop.models;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String Category;
}
