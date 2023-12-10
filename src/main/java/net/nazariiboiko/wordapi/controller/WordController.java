package net.nazariiboiko.wordapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nazariiboiko.wordapi.api.WordApi;
import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.model.WordFilterModel;
import net.nazariiboiko.wordapi.service.WordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WordController implements WordApi {
    private final WordService wordService;

    @Override
    public ResponseEntity<WordDto> getDailyWord() {
        WordDto wordDto = wordService.getDailyWord();
        return ResponseEntity.ok(wordDto);
    }

    @Override
    public ResponseEntity<WordDto> getWordById(Long id) {
        WordDto wordDto = wordService.getWord(id);
        return ResponseEntity.ok(wordDto);
    }

    @Override
    public ResponseEntity<Page<WordDto>> getPageOfWords(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<WordDto> page = wordService.getAllWords(pageable);
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<Page<WordDto>> getPageOfWordsByFilter(int pageNumber, int size, WordFilterModel filter) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        Page<WordDto> page = wordService.getWordsByFilter(pageable, filter);
        log.info("get model {}", filter);
        return ResponseEntity.ok(page);
    }
}
