package br.com.auth_service.application.service;

import br.com.auth_service.domain.entity.Role;
import br.com.auth_service.domain.enums.RoleName;
import br.com.auth_service.infrascruture.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByName(RoleName name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + name));
    }
}