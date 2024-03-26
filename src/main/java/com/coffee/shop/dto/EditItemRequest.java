package com.coffee.shop.dto;

import jakarta.validation.constraints.NotNull;

public record EditItemRequest(
        @NotNull Long id,
        @NotNull String name,
        @NotNull double price,
        @NotNull String description) { }
