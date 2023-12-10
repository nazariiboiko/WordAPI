package net.nazariiboiko.wordapi.security.jwt;

import lombok.NoArgsConstructor;
import net.nazariiboiko.wordapi.entity.UserEntity;
import net.nazariiboiko.wordapi.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {
    public static JwtUser create(UserEntity user) {
        return new JwtUser(user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(List.of(user.getRole())) );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }
}