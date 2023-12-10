package net.nazariiboiko.wordapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.nazariiboiko.wordapi.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String login;
    private String password;
    private Role role;
}
