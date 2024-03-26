package com.coffee.shop.dto;

import jakarta.validation.constraints.NotNull;

public record EditContactRequest(
        @NotNull String address,
        @NotNull String email,
        @NotNull String phone,
        @NotNull String github) { }
