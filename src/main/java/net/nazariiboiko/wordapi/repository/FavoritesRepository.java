package net.nazariiboiko.wordapi.repository;

import net.nazariiboiko.wordapi.entity.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {
    boolean existsByUserIdAndWordId(Long userId, Long wordId);
    int deleteByUserIdAndWordId(Long userId, Long wordId);
}
