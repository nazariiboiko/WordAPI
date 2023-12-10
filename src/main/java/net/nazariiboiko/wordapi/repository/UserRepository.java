package net.nazariiboiko.wordapi.repository;

import net.nazariiboiko.wordapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
    UserEntity getById(Long id);
    boolean existsByLogin(String login);
}
