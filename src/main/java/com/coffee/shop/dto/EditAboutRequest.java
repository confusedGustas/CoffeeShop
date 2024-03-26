package com.coffee.shop.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;

public record EditAboutRequest(
        @NotNull @Lob String message) { }