package net.nazariiboiko.wordapi.service;

import net.nazariiboiko.wordapi.dto.UserDto;
import net.nazariiboiko.wordapi.model.AuthRequest;
import net.nazariiboiko.wordapi.model.AuthResponse;
import net.nazariiboiko.wordapi.security.jwt.JwtUser;

public interface UserService {
    AuthResponse login(AuthRequest request);
    AuthResponse register(UserDto userDto);
    UserDto getByLogin(String login);

    JwtUser getPrincipal();
}
