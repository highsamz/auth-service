package br.com.auth_service.api.dto.request;

public record LoginRequestDTO(
        String email,
        String password
) {}
