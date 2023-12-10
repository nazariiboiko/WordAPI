package net.nazariiboiko.wordapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.api.AuthApi;
import net.nazariiboiko.wordapi.dto.UserDto;
import net.nazariiboiko.wordapi.model.AuthRequest;
import net.nazariiboiko.wordapi.model.AuthResponse;
import net.nazariiboiko.wordapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final UserService userService;

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest authRequest) {
        AuthResponse response = userService.login(authRequest);
        log.info("IN login - user:{} has logged in", authRequest.getLogin());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthResponse> register(UserDto userDto) {
        AuthResponse response = userService.register(userDto);
        log.info("IN register - user:{} has successfully registered and logged in", userDto.getLogin());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
