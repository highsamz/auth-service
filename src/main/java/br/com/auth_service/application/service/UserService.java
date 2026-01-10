package br.com.auth_service.application.service;

import br.com.auth_service.api.dto.request.CreateUserRequestDTO;
import br.com.auth_service.api.dto.response.UserResponseDTO;
import br.com.auth_service.application.mapper.UserMapper;
import br.com.auth_service.domain.entity.Role;
import br.com.auth_service.domain.entity.User;
import br.com.auth_service.application.exception.UserNotFoundException;
import br.com.auth_service.infrascruture.repository.RoleRepository;
import br.com.auth_service.infrascruture.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder, UserMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO dto) {
        Role role = roleRepository.findByName(dto.role())
                .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = mapper.toEntity(dto, role, encodedPassword);
        User saved = userRepository.save(user);
        return mapper.toResponse(saved);
    }


    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o email: " + email));
        if (!user.getEnabled()) {
            throw new IllegalStateException("Usuário já está desativado");
        }
        user.setEnabled(false);
        userRepository.save(user);
    }
}
