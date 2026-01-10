package br.com.auth_service.api.dto.request;

import br.com.auth_service.domain.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequestDTO(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotNull
        RoleName role

) {}
