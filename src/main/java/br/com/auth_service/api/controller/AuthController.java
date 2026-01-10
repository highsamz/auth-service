package br.com.auth_service.api.controller;

import br.com.auth_service.api.dto.request.LoginRequestDTO;
import br.com.auth_service.api.dto.response.LoginResponseDTO;
import br.com.auth_service.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

        private final AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(
                @RequestBody @Valid LoginRequestDTO dto
        ) {
            return ResponseEntity.ok(authService.login(dto));
        }
    }


