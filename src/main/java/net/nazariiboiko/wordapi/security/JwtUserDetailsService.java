package net.nazariiboiko.wordapi.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.entity.UserEntity;
import net.nazariiboiko.wordapi.exception.NotFoundException;
import net.nazariiboiko.wordapi.repository.UserRepository;
import net.nazariiboiko.wordapi.security.jwt.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRep;
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRep.findByLogin(username)
                .orElseThrow(() -> new NotFoundException("User not found."));

        return JwtUserFactory.create(userEntity);
    }
}
