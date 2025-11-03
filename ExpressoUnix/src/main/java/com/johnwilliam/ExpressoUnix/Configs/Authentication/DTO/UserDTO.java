package com.johnwilliam.ExpressoUnix.Configs.Authentication.DTO;

import com.johnwilliam.ExpressoUnix.Configs.Authentication.Enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotBlank @Email String login,
        @NotBlank String password,
        @NotNull UserRole role
) {}
