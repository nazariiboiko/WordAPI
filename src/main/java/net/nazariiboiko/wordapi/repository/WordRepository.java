package net.nazariiboiko.wordapi.repository;

import net.nazariiboiko.wordapi.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {

    WordEntity getById(Long id);
    @Query(value = "SELECT * FROM word ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<WordEntity> getRandomWord(@Param("limit") Long limit);
}
