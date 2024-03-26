package com.coffee.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SendEmailRequest(
        @NotNull String firstName,
        @NotNull String lastName,
        @NotNull @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") String email,
        @NotNull String message) { }
