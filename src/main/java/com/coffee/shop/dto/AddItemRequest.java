package com.coffee.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddItemRequest(
        @NotNull String name,
        @NotNull double price,
        @NotNull String description,
        @NotNull @Pattern(regexp = "^(Coffee|Snacks|Desserts)$") String category) { }
