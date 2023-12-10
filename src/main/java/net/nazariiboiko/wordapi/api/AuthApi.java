package net.nazariiboiko.wordapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.nazariiboiko.wordapi.dto.UserDto;
import net.nazariiboiko.wordapi.model.AuthRequest;
import net.nazariiboiko.wordapi.model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Authentication", description = "Authentication management APIs")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

    @Operation(summary = "Login", description = "Authenticate and log in the user.")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest);

    @Operation(summary = "Register", description = "Create a new user account.")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AuthResponse> register(@RequestBody UserDto userDto);
}
