package br.com.auth_service.application.mapper;

import br.com.auth_service.api.dto.request.CreateUserRequestDTO;
import br.com.auth_service.api.dto.response.UserResponseDTO;
import br.com.auth_service.domain.entity.Role;
import br.com.auth_service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(CreateUserRequestDTO dto, Role role, String encodedPassword) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(encodedPassword);
        user.setRoles(Collections.singleton(role));
        user.setEnabled(true);
        return user;
    }

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()),
                user.getEnabled()
        );
    }
}

