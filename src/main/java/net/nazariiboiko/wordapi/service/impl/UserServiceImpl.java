package net.nazariiboiko.wordapi.service.impl;

import lombok.RequiredArgsConstructor;
import net.nazariiboiko.wordapi.dto.UserDto;
import net.nazariiboiko.wordapi.entity.UserEntity;
import net.nazariiboiko.wordapi.enums.Role;
import net.nazariiboiko.wordapi.exception.AuthenticationException;
import net.nazariiboiko.wordapi.exception.NotFoundException;
import net.nazariiboiko.wordapi.mapper.UserMapper;
import net.nazariiboiko.wordapi.model.AuthRequest;
import net.nazariiboiko.wordapi.model.AuthResponse;
import net.nazariiboiko.wordapi.repository.UserRepository;
import net.nazariiboiko.wordapi.security.jwt.JwtTokenProvider;
import net.nazariiboiko.wordapi.security.jwt.JwtUser;
import net.nazariiboiko.wordapi.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRep;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(AuthRequest request) {
        UserEntity userEntity = userRep.findByLogin(request.getLogin())
                .orElseThrow(() -> new AuthenticationException("Invalid login or password"));
        if(passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            return new AuthResponse(jwtTokenProvider.createToken(userEntity.getLogin(), userEntity.getRole()));
        } else throw new AuthenticationException("Invalid login or password");
    }

    @Override
    public AuthResponse register(UserDto userDto) {
        if(userRep.existsByLogin(userDto.getLogin()))
            throw new AuthenticationException("User already exists.");

        UserEntity entityToCreate = new UserEntity();
        entityToCreate.setLogin(userDto.getLogin());
        entityToCreate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entityToCreate.setRole(Role.USER);
        userRep.save(entityToCreate);
        return new AuthResponse(jwtTokenProvider.createToken(entityToCreate.getLogin(), entityToCreate.getRole()));
    }

    @Override
    public UserDto getByLogin(String login) {
        return userRep.findByLogin(login)
                .map(userMapper::mapEntityToDto)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public JwtUser getPrincipal() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
