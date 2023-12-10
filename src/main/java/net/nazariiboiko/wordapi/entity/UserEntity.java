package net.nazariiboiko.wordapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import net.nazariiboiko.wordapi.enums.Role;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoritesEntity> favorites;

    @ToString.Include(name = "password")
    private String maskPassword() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++)
            sb.append("*");
        return sb.toString();
    }
}
