package br.com.auth_service.api.controller;

import br.com.auth_service.api.dto.request.CreateUserRequestDTO;
import br.com.auth_service.api.dto.response.UserResponseDTO;
import br.com.auth_service.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/email/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO dto) {
        var response = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
