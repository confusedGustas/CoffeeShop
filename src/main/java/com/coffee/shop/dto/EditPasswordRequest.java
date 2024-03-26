package com.coffee.shop.dto;

import jakarta.validation.constraints.NotNull;

public record EditPasswordRequest(
        @NotNull String oldPassword,
        @NotNull String newPassword) { }
