package net.nazariiboiko.wordapi.service;

import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.model.WordFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordService {
    Page<WordDto> getAllWords(Pageable pageable);
    Page<WordDto> getWordsByFilter(Pageable pageable, WordFilterModel model);
    WordDto getDailyWord();
    WordDto getWord(Long id);
    void updateDailyId();

}
