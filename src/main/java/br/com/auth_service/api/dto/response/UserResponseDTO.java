package br.com.auth_service.api.dto.response;

import br.com.auth_service.domain.enums.RoleName;

import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String email,
        Set<RoleName> roles,
        Boolean enabled
) {}
