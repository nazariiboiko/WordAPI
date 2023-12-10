package net.nazariiboiko.wordapi.service;

import net.nazariiboiko.wordapi.dto.WordDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService {
    void changeStatement(Long userId, Long wordId);
    Page<WordDto> getPageOfFavorites(Pageable pageable, Long userId);
}
