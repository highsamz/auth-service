package br.com.auth_service.application.service;

import br.com.auth_service.api.dto.request.LoginRequestDTO;
import br.com.auth_service.api.dto.response.LoginResponseDTO;
import br.com.auth_service.domain.entity.Role;
import br.com.auth_service.domain.entity.User;
import br.com.auth_service.domain.enums.RoleName;
import br.com.auth_service.infrascruture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!user.getEnabled()) {
            throw new RuntimeException("Usuário desativado");
        }

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        Set<RoleName> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new LoginResponseDTO(
                user.getId(),
                user.getEmail(),
                roles
        );
    }
}

