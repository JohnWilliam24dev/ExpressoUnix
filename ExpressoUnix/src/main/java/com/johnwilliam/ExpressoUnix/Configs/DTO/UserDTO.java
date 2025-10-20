package com.johnwilliam.ExpressoUnix.Configs.DTO;

import com.johnwilliam.ExpressoUnix.Configs.Enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotBlank @Email String login,
        @NotBlank String password,
        @NotNull UserRole role
) {}
